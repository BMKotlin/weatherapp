package com.viht.weathermvvm.data.remote.api

import com.viht.weathermvvm.data.remote.response.DataResponse
import com.viht.weathermvvm.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/forecast/daily")
    suspend fun getListForecastAsync(
        @Query("q") workoutId: String,
        @Query("cnt") numberForecast: Int = 7,
        @Query("appid") appId: String = Constants.KEY_APP,
        @Query("units") units: String = "metric",//celsius

    ): Response<DataResponse>
}