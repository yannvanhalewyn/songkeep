import { StatusBar } from 'expo-status-bar';
import React, { useState } from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';

export default function App() {
  const [count, setCount] = useState(0)
  return (
    <View style={styles.container}>
      <View style={styles.button}>
        <Button title="Foobar" onPress={() => {
          console.log({count: count});
          setCount(count+1);
        }}>
        </Button>
      </View>
      <View style={styles.button}>
        <Button title="Reset" color="red" onPress={() => setCount(0)}></Button>
      </View>
      <Text style={{marginTop: 8}}>Count: {count}</Text>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  button: {
    marginTop: 8,
  }
});
