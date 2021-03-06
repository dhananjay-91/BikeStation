package com.dhananjay.digikraft.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geometry(val type: String, val coordinates: List<Double>) : Parcelable
