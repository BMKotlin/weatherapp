package com.viht.weathermvvm.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.viht.weathermvvm.domain.model.DataModel
import com.viht.weathermvvm.domain.model.WeatherModel

class Converters {
    @TypeConverter
    fun toData(data: String): DataModel? {
        return Gson().fromJson(data,DataModel::class.java)
    }

    @TypeConverter
    fun fromData(data: DataModel): String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toList(list: String): List<WeatherModel>? {
        val listType = object : TypeToken<ArrayList<WeatherModel>>(){}.type
        return Gson().fromJson<List<WeatherModel>>(list,listType)
    }

    @TypeConverter
    fun fromList(weather: List<WeatherModel>): String? {
        return Gson().toJson(weather)
    }
}