package com.example.androidpeermessanger.ui.base

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpeermessanger.R

open class BaseAppCompatActivity : AppCompatActivity(){

    var bluetoothAdapter : BluetoothAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBluetooth()
        enableBluetooth()
    }

    private fun initBluetooth() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null){
            Toast.makeText(this,"No bluetooth found", Toast.LENGTH_LONG).show()
        }
    }

    fun enableBluetooth(){
        if (bluetoothAdapter != null) {
            if (!bluetoothAdapter!!.isEnabled) {
                bluetoothAdapter!!.enable()
            }
        }
    }

    fun enableDiscovery() {
        if(bluetoothAdapter != null){
            enableBluetooth()
            if (bluetoothAdapter!!.scanMode != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE){
                val discoveryIntent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
                discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
                startActivity(discoveryIntent)
            }
        }
    }
}