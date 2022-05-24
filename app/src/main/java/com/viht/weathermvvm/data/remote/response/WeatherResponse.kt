package com.viht.weathermvvm.data.remote.response

import android.os.Parcelable
import com.viht.weathermvvm.domain.model.Weather
import com.viht.weathermvvm.presentation.utils.DateUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherResponse(
    val dt: Long?,
    val pressure: Int?,
    val humidity: Int?,
    val temp: TemperatureResponse?,
    val weather: ArrayList<WeatherDescriptionResponse>?
) : Parcelable