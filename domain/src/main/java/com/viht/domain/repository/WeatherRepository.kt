package com.viht.domain.repository

import com.viht.domain.model.DataModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getListForecast(searchKey: String): Flow<ApiResult<DataModel>?>

    suspend fun fetchWeatherCached(searchKey: String): ApiResult<DataModel>?
}