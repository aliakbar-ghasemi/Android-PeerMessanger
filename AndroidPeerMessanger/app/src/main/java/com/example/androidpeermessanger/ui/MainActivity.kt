package com.example.androidpeermessanger.ui

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidpeermessanger.R
import com.example.androidpeermessanger.ui.paireddevices.DeviceListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var bluetoothAdapter : BluetoothAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBluetooth()
        enableBluetooth()
        initView()
    }

    private fun initView() {
        btnNewMessage.setOnClickListener {
            startActivity(Intent(this, DeviceListActivity::class.java))
        }
    }

    private fun initBluetooth() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null){
            Toast.makeText(this,"No bluetooth found",Toast.LENGTH_LONG).show()
        }
    }

    private fun enableBluetooth(){
        if (bluetoothAdapter != null) {
            if (!bluetoothAdapter!!.isEnabled) {
                bluetoothAdapter!!.enable()
            }
        }
    }
}