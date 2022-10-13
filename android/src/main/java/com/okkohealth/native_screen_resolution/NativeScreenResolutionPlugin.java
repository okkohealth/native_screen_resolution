package com.okkohealth.native_screen_resolution;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.graphics.Point;
import android.view.WindowMetrics;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** NativeScreenResolutionPlugin */
public class NativeScreenResolutionPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private Activity activity;
  private static final String TAG = "NativeResolution";
  
  @Override
  public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
    // TODO: your plugin is now attached to an Activity
    activity = activityPluginBinding.getActivity();
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
    // TODO: the Activity your plugin was attached to was
    // destroyed to change configuration.
    // This call will be followed by onReattachedToActivityForConfigChanges().
    activity = null;
  }

  @Override
  public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
    // TODO: your plugin is now attached to a new Activity
    // after a configuration change.
    activity = activityPluginBinding.getActivity();
  }

  @Override
  public void onDetachedFromActivity() {
    // TODO: your plugin is no longer associated with an Activity.
    // Clean up references.
    activity = null;
  }

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "native_screen_resolution");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getScreenResolution")) {

      int Measuredwidth = 0;  
      int Measuredheight = 0;  
      Point size = new Point();
       WindowManager w = activity.getWindowManager();
       Display display = w.getDefaultDisplay();
//      DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
//      size.x = displayMetrics.widthPixels;
//      size.y = displayMetrics.heightPixels;

       if (android.os.Build.VERSION.SDK_INT >= 31) {
         // include navigation bar
         WindowMetrics windowMetrics = w.getMaximumWindowMetrics();
         Rect bounds = windowMetrics.getBounds();
         size.x = bounds.width();
         size.y = bounds.height();
       }
       else if (android.os.Build.VERSION.SDK_INT >= 17) {
         // include navigation bar
         display.getRealSize(size);
       } else {
           // exclude navigation bar
           display.getSize(size);
       }
      if (size.y > size.x) {
          Measuredheight = size.y;
          Measuredwidth = size.x;
      } else {
          Measuredheight = size.x;
          Measuredwidth = size.y;
      }
      Log.i(TAG,String.format("DisplayMetrics size: %d, %d", size.x, size.y));

      result.success("{\"width\":" + Measuredwidth + ",\"height\":"+Measuredheight + "}");
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
