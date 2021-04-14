package com.example.androidpeermessanger.ui.paireddevices

import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpeermessanger.R
import com.example.androidpeermessanger.databinding.LayoutRvPairedBinding

class PairedDeviceRvAdapter : RecyclerView.Adapter<PairedDeviceRvAdapter.VH>() {
    private var list = mutableListOf<BluetoothDevice>()

    class VH(private val binding: LayoutRvPairedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bluetoothDevice: BluetoothDevice) {
            binding.device = bluetoothDevice
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
                LayoutRvPairedBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
    }

    fun setList(pairedDevices: MutableList<BluetoothDevice>) {
        this.list = pairedDevices
        notifyDataSetChanged()
    }
}