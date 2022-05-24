package com.viht.data.repository

import com.viht.domain.model.DataModel
import com.viht.domain.repository.ApiResult
import com.viht.domain.repository.WeatherRepository
import com.viht.domain.utils.DateUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val weatherHelper: com.viht.data.remote.api.WeatherHelper,
    private val weatherDao: com.viht.data.local.dao.WeatherDAO
) : ApiResponse(), WeatherRepository {

    override suspend fun getListForecast(searchKey: String): Flow<ApiResult<DataModel>?> {
        return flow {
            val cached = fetchWeatherCached(searchKey)
            if (cached is ApiResult.Success) {
                emit(cached)
            } else {
                val result = safeApiCall { weatherHelper.getListForecast(searchKey) }
                val data: DataModel = result.data?.let { com.viht.data.mapper.MainMapper.mapperData(it) } ?: DataModel(
                    arrayListOf()
                )
                //Cache to database if response is successful
                if (result is ApiResult.Success) {
                    weatherDao.insert(
                        com.viht.data.local.entity.WeatherEntity(
                            searchKey,
                            DateUtil.getDateFromNow(),
                            data
                        )
                    )
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
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getListForecastCached(searchKey: String): Flow<ApiResult<DataModel>?> {
        return flow {
            val result = fetchWeatherCached(searchKey)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchWeatherCached(searchKey: String): ApiResult<DataModel>? =
        weatherDao.getBySearchKey(searchKey, DateUtil.getDateFromNow())?.let {
            ApiResult.Success(it.weather)
        }
}