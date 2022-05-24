package com.viht.weathermvvm.domain.model

import android.os.Parcelable
import com.viht.weathermvvm.presentation.utils.DateUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherModel(
    val dt: Long?,
    val pressure: Int?,
    val humidity: Int?,
    val temp: TemperatureModel?,
    val weather: ArrayList<WeatherDescriptionModel>?
) : Parcelable{

    fun toWeather() = Weather(
        date = dt?.let { DateUtil.getDate(it) } ?: "",
        avgTemperature = temp?.getAvg() ?: 0,
        pressure = pressure ?: 0,
        humidity = humidity ?: 0,
        description = weather?.get(0)?.description ?: ""
    )
}

