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
}