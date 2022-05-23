package com.viht.weathermvvm.data.remote.response

import android.os.Parcelable
import com.viht.weathermvvm.model.Weather
import com.viht.weathermvvm.utils.DateUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherResponse(
    val dt: Long?,
    val pressure: Int?,
    val humidity: Int?,
    val temp: TemperatureResponse?,
    val weather: ArrayList<WeatherDescriptionResponse>?
) : Parcelable

fun WeatherResponse.toWeather() = Weather(
    date = dt?.let { DateUtil.getDate(it) } ?: "",
    avgTemperature = temp?.getAvg() ?: 0,
    pressure = pressure ?: 0,
    humidity = humidity ?: 0,
    description = weather?.get(0)?.description ?: ""
)