package com.viht.data.remote.api

import com.viht.data.remote.response.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/forecast/daily")
    suspend fun getListForecastAsync(
        @Query("q") workoutId: String,
        @Query("cnt") numberForecast: Int = 7,
        @Query("appid") appId: String = "60c6fbeb4b93ac653c492ba806fc346d",
        @Query("units") units: String = "metric",//celsius

    ): Response<DataResponse>
}