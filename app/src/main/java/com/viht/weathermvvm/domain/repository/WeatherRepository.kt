package com.viht.weathermvvm.domain.repository

import com.viht.weathermvvm.data.repository.ApiResult
import com.viht.weathermvvm.domain.model.DataModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getListForecast(searchKey: String): Flow<ApiResult<DataModel>?>

    suspend fun fetchWeatherCached(searchKey: String): ApiResult<DataModel>?
}