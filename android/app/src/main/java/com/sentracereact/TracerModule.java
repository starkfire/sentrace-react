package com.sentracereact;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.content.Context;
import android.content.pm.PackageManager;

class TracerModule extends ReactContextBaseJavaModule {
    
    private Context context;
    private ReactApplicationContext reactContext;

    public TracerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
    }

    @Override
    public String getName() {
        return "TracerModule";
    }

    // Check for BLE Support
    @ReactMethod
    public void checkBleSupport(Callback callback) {
        boolean bleSupported = true;

        if(!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            bleSupported = false;
        }

        callback.invoke(bleSupported);
    }
}