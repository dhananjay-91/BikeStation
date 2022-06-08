package com.dhananjay.digikraft.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dhananjay.digikraft.R
import com.dhananjay.digikraft.data.model.BikeStation
import com.dhananjay.digikraft.databinding.AdapterItemBinding

class StationAdapter(private val itemListener: StationAdapter.RecyclerItemListener) :
    RecyclerView.Adapter<StationAdapter.StationViewHolder>() {


    private var mBikeStations = ArrayList<BikeStation>()

    fun setData(list: ArrayList<BikeStation>) {
        mBikeStations.clear()
        mBikeStations.addAll(list)
        notifyDataSetChanged()
    }

    class StationViewHolder(private val binding: AdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bikeStation: BikeStation, itemListener: RecyclerItemListener) {
            binding.bikeStation = bikeStation
//            binding.txtStationId.text = bikeStation.id.toString()
//            binding.txtStationName.text = bikeStation.properties.label
//
//            binding.txtAvailableBike.text = bikeStation.properties.bikes.toString()
//            binding.txtAvailablePlaces.text = bikeStation.properties.free_racks.toString()

            binding.root.setOnClickListener {
                itemListener.onItemSelected(bikeStation)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val binding = DataBindingUtil.inflate<AdapterItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_item, parent, false
        )
        return StationViewHolder(binding)
    }


    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val song = mBikeStations[position]
        holder.bind(song, itemListener)
    }


    override fun getItemCount(): Int {
        return mBikeStations.size
    }

    interface RecyclerItemListener {
        fun onItemSelected(bikeStation: BikeStation)
    }
}
