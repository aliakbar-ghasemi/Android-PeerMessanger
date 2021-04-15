package com.example.androidpeermessanger.ui

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidpeermessanger.R
import com.example.androidpeermessanger.ui.base.BaseAppCompatActivity
import com.example.androidpeermessanger.ui.paireddevices.DeviceListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        btnNewMessage.setOnClickListener {
            startActivity(Intent(this, DeviceListActivity::class.java))
        }
    }
}