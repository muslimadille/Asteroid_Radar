package com.udacity.asteroidradar.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodayImageResponse(
    val title:String?,
    val url:String?,
    val media_type:String?
): Parcelable