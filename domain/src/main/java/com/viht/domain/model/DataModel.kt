package com.viht.domain.model

data class DataModel(
    val list: ArrayList<WeatherModel>?
) {

    fun toWeatherUI(): List<Weather> {
        return list?.map {
            it.toWeather()
        } ?: listOf()
    }

}
