package com.viht.domain.usecase

import com.viht.domain.repository.WeatherRepository
import javax.inject.Inject


class WeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend fun execute(searchKey: String) = repository.getListForecast(searchKey)
}