import 'dart:io';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:file_picker/file_picker.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:minly_task/theme/custom_assets.dart';
import 'package:minly_task/utils/constants.dart';
import 'package:minly_task/viewmodels/home_view_model.dart';
import 'package:provider/provider.dart';

///created by eslamwael74 on 5/18/2021.
///Email: eslamwael74@outlook.com

class HomeScreen extends StatefulWidget {
  HomeScreen({Key key}) : super(key: key);

  @override
  _HomeScreenState createState() {
    return _HomeScreenState();
  }
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: ChangeNotifierProvider(
        create: (_) => HomeViewModel()..getImages(),
        child: Scaffold(
          appBar: CupertinoNavigationBar(
            trailing: Builder(builder: (context) {
              return TextButton.icon(
                  onPressed: () => _onAddPhotoClicked(context),
                  icon: Icon(
                    CupertinoIcons.add_circled,
                    size: 20,
                  ),
                  label: Text('Add Photo'));
            }),
          ),
          body: Consumer<HomeViewModel>(builder: (context, viewModel, _) {
            if (viewModel.busy) {
              return Center(child: CupertinoActivityIndicator());
            }
            if (viewModel.images.isEmpty) {
              return Center(
                child: Text('No Images, Try to upload one'),
              );
            }
            return _buildImagesWidget(viewModel.images);
          }),
        ),
      ),
    );
  }

  _buildImagesWidget(List<String> images) => Container(
        child: Padding(
          padding: const EdgeInsets.all(18.0),
          child: GridView.builder(
              itemCount: images.length,
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
                mainAxisSpacing: 18.0,
                crossAxisSpacing: 18.0,
              ),
              itemBuilder: (context, index) {
                return _buildImageItem(imageUrl: images[index]);
              }),
        ),
      );

  _buildImageItem({@required String imageUrl}) => CachedNetworkImage(
        imageUrl: "${Constants.baseUrl}images/$imageUrl",
        placeholder: (context, url) => _buildImagePlaceHolder(),
        errorWidget: (context, url, error) => _buildImagePlaceHolder(),
        fit: BoxFit.cover,
        alignment: Alignment.topCenter,
      );

  _buildImagePlaceHolder() => Image.asset(
        CustomAssets.placeHolder,
        width: MediaQuery.of(context).size.width,
        fit: BoxFit.cover,
        alignment: Alignment.center,
      );

  ///Open file picker and upload new image
  _onAddPhotoClicked(context) async {
    FilePickerResult result = await FilePicker.platform.pickFiles(
      type: FileType.image
    );

    if (result != null) {
      File file = File(result.files.single.path);
      bool isUploaded = await Provider.of<HomeViewModel>(context, listen: false)
          .uploadImage(file);
      showCupertinoDialog(
          context: context,
          builder: (context) {
            return CupertinoAlertDialog(
              content: Text(
                  "${isUploaded ? "Image Uploaded" : "Upload Failed, Try Again!"}"),
              actions: <Widget>[
                CupertinoDialogAction(
                  isDefaultAction: true,
                  onPressed: () => Navigator.pop(this.context),
                  child: Text("Done"),
                ),
              ],
            );
          });
    } else {
      // User canceled the picker
    }
  }
}
