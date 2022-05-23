package com.viht.weathermvvm.data.remote.api

import javax.inject.Inject

class WeatherHelper @Inject constructor(private val weatherService: WeatherService) {

    suspend fun getListForecast(search: String) = weatherService.getListForecastAsync(search)
}