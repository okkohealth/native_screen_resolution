import 'dart:async';

import 'package:flutter/services.dart';

class NativeScreenResolution {
  static const MethodChannel _channel = const MethodChannel('native_screen_resolution');

  static Future<String> get screenResolution async {
    final String version = await _channel.invokeMethod('getScreenResolution');
    return version;
  }
}
