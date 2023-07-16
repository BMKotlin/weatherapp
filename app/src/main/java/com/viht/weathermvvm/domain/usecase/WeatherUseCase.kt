package com.viht.weathermvvm.domain.usecase

import com.viht.weathermvvm.data.repository.ApiResult
import com.viht.weathermvvm.domain.model.DataModel
import com.viht.weathermvvm.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class WeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(searchKey: String): Flow<ApiResult<DataModel>?> {
        return repository.getListForecast(searchKey)
    }

    suspend fun execute(searchKey: String) = repository.getListForecast(searchKey)
}