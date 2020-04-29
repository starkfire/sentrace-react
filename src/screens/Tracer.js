import React, { Component } from 'react';

import { StyleSheet, View } from 'react-native';

import { Text } from 'galio-framework';

import { BleManager } from 'react-native-ble-plx';

export default class Tracer extends Component {
  constructor() {
    super()
    this.manager = new BleManager()
    this.state = {
      info: '',
      values: {},
      devices: []
    }
  }

  info(message) {
    this.setState({ info: message })
  }

  error(message) {
    this.setState({ info: 'ERR: ' + message })
  }

  componentWillMount() {
    this.scanDevices()
  }

  async scanDevices() {
    this.manager.startDeviceScan(null, null, (error, device) => {
      if (error) {
        // error summary
        switch (error.errorCode) {
          case 102:
            return this.info('Bluetooth is powered off')
          case 101:
            return this.info('App is unauthorized to use BLE')
          case 100:
            return this.info('Device does not have BLE support')
        }

        // report error on console
        console.log(JSON.stringify(error))
        return
      }

      // display detections on console
      this.info('Scanning for Devices...')
      console.log(`${device.id} : ${device.rssi}`)

      // save detections on state
      let detection = this.state.devices.concat({
        deviceId: device.id,
        deviceRssi: device.rssi
      })
      this.setState({ devices: detection })

      // display all detections on console
      console.log(...this.state.devices)
    })
  }

  render() {
    return (
      <View>
        <Text style={styles.results}>
          {this.state.info}
        </Text>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  header: {
    backgroundColor: '#222'
  },
  results: {
    color: '#fff',
    textAlign: 'center',
    fontSize: 18
  }
})