package com.viht.weathermvvm.repository

import com.viht.weathermvvm.data.local.dao.WeatherDAO
import com.viht.weathermvvm.data.local.entity.WeatherEntity
import com.viht.weathermvvm.data.remote.api.WeatherHelper
import com.viht.weathermvvm.data.remote.response.DataResponse
import com.viht.weathermvvm.di.IoDispatcher
import com.viht.weathermvvm.utils.DateUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherHelper: WeatherHelper,
    private val weatherDao: WeatherDAO,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    networkManager: NetworkManager
) : ApiResponse(networkManager) {

    suspend fun getListForecast(searchKey: String): Flow<ApiResult<DataResponse>> {
        return flow {
            val cached = fetchWeatherCached(searchKey)
            if (cached is ApiResult.Success) {
                emit(cached)
            } else {
                val result = safeApiCall { weatherHelper.getListForecast(searchKey) }
                //Cache to database if response is successful
                if (result is ApiResult.Success) {
                    result.data?.let { it ->
                        weatherDao.deleteAll()
                        weatherDao.insert(WeatherEntity(searchKey, DateUtil.getDateFromNow(), it))
                    }
                }
                emit(result)
            }
        }.flowOn(dispatcher)//Dispatchers.IO
    }

    suspend fun getListForecastCached(searchKey: String): Flow<ApiResult<DataResponse>?> {
        return flow {
            val result = fetchWeatherCached(searchKey)
            emit(result)
        }.flowOn(dispatcher)//Dispatchers.IO
    }

    suspend fun fetchWeatherCached(searchKey: String): ApiResult<DataResponse>? =
        weatherDao.getBySearchKey(searchKey, DateUtil.getDateFromNow())?.let {
            ApiResult.Success(it.weather)
        }
}