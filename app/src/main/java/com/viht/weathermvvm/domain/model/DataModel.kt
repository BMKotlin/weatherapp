package com.viht.weathermvvm.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    val list: ArrayList<WeatherModel>?
) : Parcelable {

    fun toWeatherUI(): List<Weather> {
        return list?.map {
            it.toWeather()
        } ?: listOf()
    }

}
