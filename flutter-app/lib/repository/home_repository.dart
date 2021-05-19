import 'dart:convert';
import 'dart:io';

import 'package:dio/dio.dart';
import 'package:minly_task/web_services/end_points.dart';
import 'package:minly_task/web_services/error_helper.dart';
import 'package:minly_task/web_services/http_client.dart';

///created by eslamwael74 on 5/18/2021.
///Email: eslamwael74@outlook.com
class HomeRepository {
  const HomeRepository();

  Future<List<String>> fetchImages() async {
    try {
      final response = await HttpClient.getInstance().get(EndPoints.listImages);
      var data = response.data;
      List<String> images = data != null ? List.from(data) : null;
      return images;
    } catch (e) {
      print(e.toString());
      throw ExceptionHelper.parse(err: e);
    }
  }

Future<bool> uploadImage(File file) async {
  try {
    FormData data = FormData.fromMap({});
    data.files.add(MapEntry(
        'image', MultipartFile.fromFileSync(file.path, filename: file.path)));

    final response = await HttpClient.getInstance()
        .post(EndPoints.uploadImage, data: data);
    return response.data != null;
  } catch (e) {
    throw ExceptionHelper.parse(err: e);
  }
}
}
