package com.dhananjay.digikraft.data.remote.api


import com.dhananjay.digikraft.data.remote.response.BikeStationResponse
import retrofit2.http.GET

interface ApiService {

    @GET("mim/plan/map_service.html?mtype=pub_transport&co=stacje_rowerowe")
    suspend fun getBikeStations(
    ): BikeStationResponse


}