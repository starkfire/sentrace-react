package com.sentracereact;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;

import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.BluetoothLeAdvertiser;

import android.util.Log;

class TracerModule extends ReactContextBaseJavaModule {

    private static final int REQUEST_ENABLE_BT = 539;

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothManager bluetoothManager;
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

    // Bluetooth Adapter
    private BluetoothAdapter getBluetoothAdapter() {
        if (bluetoothAdapter == null) {
            BluetoothManager manager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
            bluetoothAdapter = manager.getAdapter();
        }

        return bluetoothAdapter;
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

    // Enable Bluetooth
    @ReactMethod
    public void enableBluetooth(Callback callback) {
        if (getBluetoothAdapter() == null) {
            callback.invoke("No Bluetooth Found on Device");
            return;
        }

        if (!getBluetoothAdapter().isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (getCurrentActivity() == null)
                callback.invoke("Current Activity Not Available");
            else
                getCurrentActivity().startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            callback.invoke();
        }
    }
}