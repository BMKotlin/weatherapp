package com.viht.domain.model

data class Weather(
    val date: String,
    val avgTemperature: Int,
    val pressure: Int,
    val humidity: Int,
    val description: String
) {
    fun areSameContent(other: Weather): Boolean {
        return date == other.date
                && avgTemperature == other.avgTemperature
                && pressure == other.pressure
                && humidity == other.humidity
                && description == other.description
    }
}