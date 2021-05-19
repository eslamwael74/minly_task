import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:minly_task/screens/home_screen.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return CupertinoApp(
      debugShowCheckedModeBanner: false,
      // theme: ThemeData(
      //   platform: TargetPlatform.iOS,
      //   primarySwatch: Colors.blue,
      // ),
      home: HomeScreen(),
    );
  }
}
