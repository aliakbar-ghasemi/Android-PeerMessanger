package com.example.androidpeermessanger.ui.paireddevices

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.androidpeermessanger.R
import com.example.androidpeermessanger.ui.base.BaseAppCompatActivity
import kotlinx.android.synthetic.main.activity_device_list.*

class DeviceListActivity : BaseAppCompatActivity() {
    private var pairedDeviceRvAdapter: PairedDeviceRvAdapter? = null
    private var discoveryDeviceRvAdapter: PairedDeviceRvAdapter? = null

    override fun onStart() {
        super.onStart()
        registerReceiver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)

        initVariables()
    }

    private fun initVariables() {

        pairedDeviceRvAdapter = PairedDeviceRvAdapter()
        rvPairedDevices.adapter = pairedDeviceRvAdapter

        discoveryDeviceRvAdapter = PairedDeviceRvAdapter()
        rvDiscoveryDevices.adapter = discoveryDeviceRvAdapter

        enableDiscovery()
        getPairedDevices()
        txtScan.setOnClickListener {
            scanDevices()
        }
    }

    private fun getPairedDevices() {
        var pairedDevices = mutableListOf<BluetoothDevice>()
        var paired = bluetoothAdapter?.bondedDevices

        paired?.forEach {
            pairedDevices.add(it)
        }

        if (pairedDevices.size > 0){
            txtNoPairedDevice.visibility = GONE
        } else{
            txtNoPairedDevice.visibility = VISIBLE
        }
        pairedDeviceRvAdapter?.setList(pairedDevices)

    }

    private fun scanDevices(){
        enableDiscovery()
        if(bluetoothAdapter != null){
            if (bluetoothAdapter!!.isDiscovering){
                bluetoothAdapter!!.cancelDiscovery()
            }
            bluetoothAdapter!!.startDiscovery()
            pbScan.visibility = VISIBLE

            registerReceiver()
        }
    }

    private var bluetoothBroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            txtNoNewDevice.visibility = GONE
            val action = intent?.action
            Log.d("##bluetoothBroadcast", "onReceive: $action")
            if(BluetoothDevice.ACTION_FOUND == action){
                val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                Log.d("##bluetoothBroadcast", "onReceive: $device")
                if (device != null) {
                    Log.d("##bluetoothBroadcast", "device: ${device.bondState}")
                    if (device.bondState != BluetoothDevice.BOND_BONDED){
                        discoveryDeviceRvAdapter?.add(device)
                    }
                }
            } else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED == action){
                pbScan.visibility = GONE
                if (discoveryDeviceRvAdapter != null){
                    if(discoveryDeviceRvAdapter!!.itemCount == 0){
                        txtNoNewDevice.visibility = VISIBLE
                        //Toast.makeText(this@DeviceListActivity,"No Item Found",Toast.LENGTH_LONG).show()
                    }

                }
            }
        }
    }

    private fun registerReceiver(){
        var intentFilterFound = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(bluetoothBroadcastReceiver, intentFilterFound)

        var intentFilterFinished = IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        registerReceiver(bluetoothBroadcastReceiver, intentFilterFinished)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(bluetoothBroadcastReceiver)
    }
}