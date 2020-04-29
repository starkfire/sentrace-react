import React from 'react';
import { StyleSheet } from 'react-native';

import { Block } from 'galio-framework';

import Tracer from './screens/Tracer'

const App = () => {
  return (
    <Block safe flex style={styles.mainContainer}>
      <Tracer />
    </Block>
  )
}

const styles = StyleSheet.create({
  mainContainer: {
    backgroundColor: '#222'
  }
})

export default App;