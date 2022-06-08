package com.dhananjay.digikraft.data.remote.api

import com.dhananjay.digikraft.data.remote.response.BikeStationResponse


class ApiHelper(private val apiService: ApiService) {

    suspend fun getAllBikeStations(): BikeStationResponse {
        return apiService.getBikeStations()
    }

}