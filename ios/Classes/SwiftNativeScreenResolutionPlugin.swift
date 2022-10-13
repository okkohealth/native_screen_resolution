import Flutter
import UIKit

public class SwiftNativeScreenResolutionPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "native_screen_resolution", binaryMessenger: registrar.messenger())
    let instance = SwiftNativeScreenResolutionPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    let screenRect = UIScreen.main.nativeBounds
    let screenWidth = screenRect.size.width
    let screenHeight = screenRect.size.height

    result("{\"width\":\(screenWidth),\"height\":\(screenHeight)}")
  }
}
