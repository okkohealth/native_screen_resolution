import Flutter
import UIKit

public class SwiftNativeScreenResolutionPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "native_screen_resolution", binaryMessenger: registrar.messenger())
    let instance = SwiftNativeScreenResolutionPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    let screenRect = UIScreen.main.bounds
    let screenWidth = screenRect.size.width*2
    let screenHeight = screenRect.size.height*2

    result("{\"width\":\(screenWidth),\"height\":\(screenHeight)}")
  }
}
