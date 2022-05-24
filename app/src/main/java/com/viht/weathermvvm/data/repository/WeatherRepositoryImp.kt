package com.viht.weathermvvm.data.repository

import com.viht.weathermvvm.data.local.dao.WeatherDAO
import com.viht.weathermvvm.data.local.entity.WeatherEntity
import com.viht.weathermvvm.data.mapper.MainMapper
import com.viht.weathermvvm.data.remote.api.WeatherHelper
import com.viht.weathermvvm.di.IoDispatcher
import com.viht.weathermvvm.domain.model.DataModel
import com.viht.weathermvvm.domain.repository.WeatherRepository
import com.viht.weathermvvm.presentation.utils.DateUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val weatherHelper: WeatherHelper,
    private val weatherDao: WeatherDAO,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ApiResponse(), WeatherRepository {

    override suspend fun getListForecast(searchKey: String): Flow<ApiResult<DataModel>?> {
        return flow {
            val cached = fetchWeatherCached(searchKey)
            if (cached is ApiResult.Success) {
                emit(cached)
            } else {
                val result = safeApiCall { weatherHelper.getListForecast(searchKey) }
                val data: DataModel = result.data?.let { MainMapper.mapperData(it) } ?: DataModel(
                    arrayListOf()
                )
                //Cache to database if response is successful
                if (result is ApiResult.Success) {
                    weatherDao.insert(WeatherEntity(searchKey, DateUtil.getDateFromNow(), data))
                    emit(
                        ApiResult.Success(
                            data = data
                        )
                    )
                } else {
                    emit(result.message?.let {
                        ApiResult.Error(
                            message = it,
                            data = data
                        )
                    })
                }
            }
        }.flowOn(dispatcher)//Dispatchers.IO
    }

    suspend fun getListForecastCached(searchKey: String): Flow<ApiResult<DataModel>?> {
        return flow {
            val result = fetchWeatherCached(searchKey)
            emit(result)
        }.flowOn(dispatcher)//Dispatchers.IO
    }

    override suspend fun fetchWeatherCached(searchKey: String): ApiResult<DataModel>? =
        weatherDao.getBySearchKey(searchKey, DateUtil.getDateFromNow())?.let {
            ApiResult.Success(it.weather)
        }
}