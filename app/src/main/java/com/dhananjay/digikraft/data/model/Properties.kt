package com.dhananjay.digikraft.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Properties(val free_racks: Int, val bikes: Int, val label: String, val bike_racks: Int) : Parcelable
