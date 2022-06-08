package com.dhananjay.digikraft.data.repository

import com.dhananjay.digikraft.data.model.BikeStation
import com.dhananjay.digikraft.data.remote.api.ApiHelper

class StationRepo(private val apiHelper: ApiHelper) {

    var mStations: ArrayList<BikeStation>? = null

    suspend fun getStationData(): ArrayList<BikeStation> {

        if (mStations == null) {
            val response = apiHelper.getAllBikeStations()
            mStations = response.features
        }
        return mStations as ArrayList<BikeStation>
    }

}