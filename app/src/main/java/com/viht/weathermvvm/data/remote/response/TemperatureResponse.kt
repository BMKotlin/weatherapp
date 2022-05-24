package com.viht.weathermvvm.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TemperatureResponse(
    val min: Float?,
    val max: Float?
) : Parcelable