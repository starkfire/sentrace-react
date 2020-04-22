# Sentrace

### **A BLE (Bluetooth Low Energy) Contact Tracer built on React Native**

## Development
Clone the repository
```
git clone https://github.com/starkfire/sentrace-react.git
cd sentrace-react
```
Install dependencies
```
npm install
npx react-native link react-native-ble-plx
```
Run the app
```
npx react-native run-android
```
You can test the app on a device through USB, `adb`, or Wi-Fi (see [guide](https://reactnative.dev/docs/running-on-device)).

## Troubleshooting
#### _Device Unauthorized to use BluetoothLE_
Enable location permissions for the app (see [Issue](https://github.com/Polidea/react-native-ble-plx/issues/248)).