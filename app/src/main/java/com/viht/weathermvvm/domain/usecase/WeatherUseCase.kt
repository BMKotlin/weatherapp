package com.viht.weathermvvm.domain.usecase

import com.viht.weathermvvm.domain.repository.WeatherRepository
import javax.inject.Inject


class WeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend fun execute(searchKey: String) = repository.getListForecast(searchKey)
}