import React, { Component } from 'react'

import {
  PermissionsAndroid,
  Platform,
  View,
  Text
} from 'react-native'

export default class Tracer extends Component {
  constructor() {
    super()
    this.state = {
      isScanning: false,
      isBleEnabled: false,
      isLocationEnabled: false,
      peripherals: new Map(),
      appState: ''
    }
  }

  componentWillMount() {
    this.checkLocationPermissions()
  }

  /* Get Location Permissions */
  async checkLocationPermissions() {
    if (Platform.OS === 'android') {
      PermissionsAndroid.check(PermissionsAndroid.PERMISSIONS.ACCESS_COARSE_LOCATION)
        .then((result) => result ? this.setState({ isLocationEnabled: true }) : this.requestLocationPermissions())
    }
  }

  async requestLocationPermissions() {
    try {
      const granted = await PermissionsAndroid.request(PermissionsAndroid.PERMISSIONS.ACCESS_COARSE_LOCATION)

      if (granted === PermissionsAndroid.RESULTS.GRANTED) {
        this.setState({ isLocationEnabled: true })
        console.log('OK 2')
      } else {
        console.log('Denied')
      }
    } catch (err) {
      console.log(err)
    }
  }

  render() {
    return (
      <View>
        <Text></Text>
      </View>
    )
  }
}