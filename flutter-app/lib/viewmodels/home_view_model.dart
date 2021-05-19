import 'dart:developer';
import 'dart:io';

import 'package:minly_task/repository/home_repository.dart';
import 'package:minly_task/viewmodels/base_model.dart';
import 'package:minly_task/web_services/error_helper.dart';

///created by eslamwael74 on 5/18/2021.
///Email: eslamwael74@outlook.com
class HomeViewModel extends BaseViewModel {
  var repository = HomeRepository();
  List<String> images = [];

  Future<void> getImages() async {
    try {
      busy = true;

      final images = await repository.fetchImages();
      this.images = images;

      setBusy(false);
    } catch (e) {
      if (e is ExceptionHelper) {
        setError(exceptionHelper: e);
      }
      log('getImages--->${e.toString()}');
      setBusy(false);
    }
  }

  Future<bool> uploadImage(File file) async {
    try {
      setBusy(true);

      bool isUploaded = await repository.uploadImage(file);

      if (isUploaded) {
        await getImages();
        return true;
      }
      return false;
    } catch (e) {
      if (e is ExceptionHelper) {
        setError(exceptionHelper: e);
      }
      return false;
    }
  }
}
