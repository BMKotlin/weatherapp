package com.viht.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherResponse(
    val dt: Long?,
    val pressure: Int?,
    val humidity: Int?,
    val temp: TemperatureResponse?,
    val weather: ArrayList<WeatherDescriptionResponse>?
) : Parcelable