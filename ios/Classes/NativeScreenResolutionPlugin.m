#import "NativeScreenResolutionPlugin.h"
#if __has_include(<native_screen_resolution/native_screen_resolution-Swift.h>)
#import <native_screen_resolution/native_screen_resolution-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "native_screen_resolution-Swift.h"
#endif

@implementation NativeScreenResolutionPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftNativeScreenResolutionPlugin registerWithRegistrar:registrar];
}
@end
