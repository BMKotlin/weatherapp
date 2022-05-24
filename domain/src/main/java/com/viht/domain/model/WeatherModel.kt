package com.viht.domain.model

import com.viht.domain.utils.DateUtil

data class WeatherModel(
    val dt: Long?,
    val pressure: Int?,
    val humidity: Int?,
    val temp: TemperatureModel?,
    val weather: ArrayList<WeatherDescriptionModel>?
) {

    fun toWeather() = Weather(
        date = dt?.let { DateUtil.getDate(it) } ?: "",
        avgTemperature = temp?.getAvg() ?: 0,
        pressure = pressure ?: 0,
        humidity = humidity ?: 0,
        description = weather?.get(0)?.description ?: ""
    )
}

