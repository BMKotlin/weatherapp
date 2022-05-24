package com.viht.weathermvvm.domain.model

import android.os.Parcelable
import com.viht.weathermvvm.domain.model.Weather
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
