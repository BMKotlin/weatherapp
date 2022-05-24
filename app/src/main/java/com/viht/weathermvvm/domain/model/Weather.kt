package com.viht.weathermvvm.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val date: String,
    val avgTemperature: Int,
    val pressure: Int,
    val humidity: Int,
    val description: String
) : Parcelable {
    fun areSameContent(other: Weather): Boolean {
        return date == other.date
                && avgTemperature == other.avgTemperature
                && pressure == other.pressure
                && humidity == other.humidity
                && description == other.description
    }
}