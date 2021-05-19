import '../utils/constants.dart';
import 'package:dio/dio.dart';

class HttpClient {
  static Dio _dio;
  static String token;

  static Dio getInstance() {
    if (_dio == null) {
      BaseOptions baseOptions = new BaseOptions(baseUrl: Constants.baseUrl);
      _dio = new Dio(baseOptions);
      _dio.interceptors.add(LogInterceptor(
        request: true,
        responseBody: true,
        requestBody: true,
        requestHeader: true,
      ));
      _dio.interceptors
          .add(InterceptorsWrapper(onRequest: (RequestOptions options) {
        // if (token != null) {
        //   options.headers['Authorization'] = token;
        // }
        // Do something before request is sent
        return options; //continue
      }, onResponse: (Response response) {
        // Do something with response data
        return response; // continue
      }, onError: (DioError e) {
        // Do something with response error
        return e; //continue
      }));
    }

    return _dio;
  }
}
