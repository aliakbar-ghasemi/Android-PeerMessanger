package com.example.androidpeermessanger.ui.paireddevices

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidpeermessanger.R
import kotlinx.android.synthetic.main.activity_device_list.*

class DeviceListActivity : AppCompatActivity() {
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var pairedDeviceRvAdapter: PairedDeviceRvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)

        initVariables()
    }

    private fun initVariables() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        pairedDeviceRvAdapter = PairedDeviceRvAdapter()
        rvPairedDevices.adapter = pairedDeviceRvAdapter

        getPairedDevices()
    }

    private fun getPairedDevices() {
        var pairedDevices = mutableListOf<BluetoothDevice>()
        var paired = bluetoothAdapter?.bondedDevices

        paired?.forEach {
            pairedDevices.add(it)
        }

        pairedDeviceRvAdapter?.setList(pairedDevices)
    }
}