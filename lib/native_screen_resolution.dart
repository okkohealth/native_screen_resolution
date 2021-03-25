import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class NativeScreenResolution {
  static const MethodChannel _channel = const MethodChannel('native_screen_resolution');

  static Future<Size> get screenResolution async {
    final String version = await _channel.invokeMethod('getScreenResolution');
    Map<String, dynamic> map = jsonDecode(version);
    double width = (map["width"] is int) ? (map["width"] as int).toDouble() : map["width"];
    double height = (map["height"] is int) ? (map["height"] as int).toDouble() : map["height"];
    Size size = Size(width, height);
    return size;
  }
}
