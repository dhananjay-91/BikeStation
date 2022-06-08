package com.dhananjay.digikraft.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BikeStation(val id: Long, val geometry: Geometry, val properties: Properties) :
    Parcelable
