# Android Peer Messanger

## Introduction
Simple messaging application via bluetooth.

### roadmap of developments
<h3 style="margin: 10px">follow this steps:</h3>

<!-- Step1: Requesting Bluetooth permissions -->
<details style="margin-top: 4px">
<summary style="border-radius: 5px; background-color: #302f2f; color: #ffa500; padding:10px 20px">Step1: Requesting Bluetooth permissions</summary>
  
```xml
<uses-permission android:name="android.permission.BLUETOOTH"/>
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
```
</details>

<!-- Step2: Checking if device supports Bluetooth -->
<details style="margin-top: 4px">
<summary style="border-radius: 5px; background-color: #302f2f; color: #ffa500; padding:10px 20px">Step2: Checking if device supports Bluetooth</summary>
  
```kotlin
    private fun initBluetooth() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null){
            Toast.makeText(this,"No bluetooth found",Toast.LENGTH_LONG).show()
        }
    }
```
</details>

<!-- Step3: Check if Bluetooth is Enabled -->
<details style="margin-top: 4px">
<summary style="border-radius: 5px; background-color: #302f2f; color: #ffa500; padding:10px 20px">Step3: Check if Bluetooth is Enabled</summary>
  
```kotlin
    private fun enableBluetooth(){
        if (bluetoothAdapter != null) {
            if (!bluetoothAdapter!!.isEnabled) {
                bluetoothAdapter!!.enable()
            }
        }
    }
```
</details>

<!-- Step4: Listing paired devices -->
<details style="margin-top: 4px">
<summary style="border-radius: 5px; background-color: #302f2f; color: #ffa500; padding:10px 20px">Step4: Listing paired devices</summary>
  
```kotlin
    var paired = bluetoothAdapter?.bondedDevices
```
</details>
