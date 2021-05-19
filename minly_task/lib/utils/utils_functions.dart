import 'dart:math';

import 'package:flutter/material.dart';
import 'package:minly_task/app/app_keys.dart';

class UtilsFunctions {
  static showSnackBar({
    String text = 'Something Wrong',
    Color color = Colors.red,
    Widget icon = const Icon(
      Icons.error,
      color: Colors.white,
    ),
  }) {
    AppKeys.rootScaffoldMessengerKey.currentState
      ..hideCurrentSnackBar()
      ..showSnackBar(
        SnackBar(
          content: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [Text(text), icon],
          ),
          backgroundColor: color,
        ),
      );
  }

  String twoDigits(int n) {
    if (n >= 10) return "${n}";
    return "0${n}";
  }

  int toTwelveHour(int n) {
    return n > 12 ? n % 12 : (n == 0 ? 12 : n);
  }

  String amOrPm(int n) {
    return n >= 12 ? 'p.m.' : 'a.m.';
  }

  final List<String> months = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'Devember'
  ];

  String computeHowLongAgoText(DateTime timestamp) {
    DateTime now = DateTime.now();

    Duration difference = now.difference(timestamp);

    if (difference.inSeconds < 60) {
      return 'Just Now';
    } else if (difference.inMinutes < 60) {
      return '${difference.inMinutes} minute${difference.inMinutes > 1 ? 's' : ''} ago';
    } else if (difference.inHours < 6) {
      return '${difference.inHours} hour${difference.inHours > 1 ? 's' : ''} ago';
    } else {
      bool sameDay = new DateTime(now.year, now.month, now.day) ==
          new DateTime(timestamp.year, timestamp.month, timestamp.day);

      String onText =
          sameDay ? 'Today' : 'on ${months[timestamp.month]} ${timestamp.day}';
      return 'At ${toTwelveHour(timestamp.hour)}:${twoDigits(timestamp.minute)} ${amOrPm(timestamp.hour)} ${onText}';
    }
  }

  String computeHowLongAgoTextShort(DateTime timestamp) {
    DateTime now = DateTime.now();

    Duration difference = now.difference(timestamp);

    if (difference.inSeconds < 60) {
      return 'Just Now';
    } else if (difference.inMinutes < 60) {
      return '${difference.inMinutes} minute${difference.inMinutes > 1 ? 's' : ''} ago';
    } else if (difference.inHours < 6) {
      return '${difference.inHours} hour${difference.inHours > 1 ? 's' : ''} ago';
    } else {
      bool sameDay = new DateTime(now.year, now.month, now.day) ==
          new DateTime(timestamp.year, timestamp.month, timestamp.day);

      String onText =
          sameDay ? 'Today' : 'On ${months[timestamp.month]} ${timestamp.day}';
      return '${onText}';
    }
  }

  static changeScreen(BuildContext context, Widget widget) {
    Navigator.push(context, MaterialPageRoute(builder: (context) => widget));
  }

  static changeScreenReplacement(BuildContext context, Widget widget) {
    Navigator.pushReplacement(
        context, MaterialPageRoute(builder: (context) => widget));
  }

  static int generateRandomNumber(int max) {
    var rng = new Random();
    return rng.nextInt(max);
  }
}
