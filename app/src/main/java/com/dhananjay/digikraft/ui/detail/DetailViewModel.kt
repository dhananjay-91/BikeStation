package com.dhananjay.digikraft.ui.detail

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhananjay.digikraft.R
import com.dhananjay.digikraft.data.model.BikeStation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DetailViewModel : ViewModel(), OnMapReadyCallback {

    val stationData: MutableLiveData<BikeStation> = MutableLiveData<BikeStation>()
    val createMarkerView: MutableLiveData<String> = MutableLiveData()

    lateinit var mMap: GoogleMap


    fun showStationData(bikeStation: BikeStation) {
        stationData.value = bikeStation
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map
        createMarkerView.value = stationData.value?.properties?.bikes.toString()
    }


    fun addMarkerToGivenStationCoordinate(bitmap: Bitmap) {
        stationData.value?.geometry?.coordinates?.let {
            val location = LatLng(it[0], it[1])
            val options = MarkerOptions()
                .position(location)
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))

            mMap.addMarker(options)

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 8.0f))

        }
    }


}