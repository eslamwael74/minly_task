import 'package:flutter/material.dart';
import 'package:minly_task/web_services/error_helper.dart';

/**
 * Created by EslamWael74 on 12/5/19.
 * Email: eslamwael74@outlook.com
 */
abstract class BaseViewModel with ChangeNotifier {
  //TODO if you need handle the base progress changes or errors provide setters getters for your values and notify your listener

  bool _busy = false;
  String _error = '';

  bool get busy => _busy;
  String get error => _error;


  set busy(bool value) {
    _busy = value;
  }

  void setBusy(bool value) {
    _busy = value;
    notifyListeners();
  }

  void setError({ExceptionHelper exceptionHelper}) {
    if (exceptionHelper != null) {
      _error = exceptionHelper.message;
    }
    setBusy(false);
  }
}
