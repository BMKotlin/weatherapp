package com.viht.weathermvvm.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.viht.weathermvvm.data.remote.response.DataResponse
import com.viht.weathermvvm.data.remote.response.TemperatureResponse
import com.viht.weathermvvm.data.remote.response.WeatherDescriptionResponse
import com.viht.weathermvvm.data.remote.response.WeatherResponse

class Converters {
    @TypeConverter
    fun toData(data: String): DataResponse? {
        return Gson().fromJson<DataResponse>(data,DataResponse::class.java)
    }

    @TypeConverter
    fun fromData(data: DataResponse): String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toList(list: String): List<WeatherResponse>? {
        val listType = object : TypeToken<ArrayList<WeatherResponse>>(){}.type
        return Gson().fromJson<List<WeatherResponse>>(list,listType)
    }

    @TypeConverter
    fun fromList(weather: List<WeatherResponse>): String? {
        return Gson().toJson(weather)
    }

    @TypeConverter
    fun toTemp(temp: String): TemperatureResponse? {
        return Gson().fromJson<TemperatureResponse>(temp,TemperatureResponse::class.java)
    }

    @TypeConverter
    fun fromTemp(temp: TemperatureResponse): String? {
        return Gson().toJson(temp)
    }

    @TypeConverter
    fun toWeather(weather: String): List<WeatherDescriptionResponse>? {
        val listType = object : TypeToken<ArrayList<WeatherDescriptionResponse>>(){}.type
        return Gson().fromJson<List<WeatherDescriptionResponse>>(weather,listType)
    }

    @TypeConverter
    fun fromWeather(weather: List<WeatherDescriptionResponse>): String? {
        return Gson().toJson(weather)
    }
}