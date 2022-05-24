package com.viht.data.mapper

import com.viht.data.remote.response.DataResponse
import com.viht.data.remote.response.TemperatureResponse
import com.viht.data.remote.response.WeatherDescriptionResponse
import com.viht.data.remote.response.WeatherResponse
import com.viht.domain.model.*

object MainMapper {

    fun mapperTemperature(response: TemperatureResponse): TemperatureModel {
        return TemperatureModel(
            min = response.min,
            max = response.max
        )
    }

    fun mapperWeatherDescription(response: WeatherDescriptionResponse): WeatherDescriptionModel {
        return WeatherDescriptionModel(
            main = response.main,
            description = response.description
        )
    }

    fun mapperWeatherDescriptionList(arr: ArrayList<WeatherDescriptionResponse>): ArrayList<WeatherDescriptionModel>{
        val output = arr.map {
            mapperWeatherDescription(it)
        }
        return output as ArrayList<WeatherDescriptionModel>
    }

    fun mapperWeather(response: WeatherResponse): WeatherModel {
        return WeatherModel(
            dt = response.dt,
            pressure = response.pressure,
            humidity = response.humidity,
            temp = response.temp?.let { mapperTemperature(it) },
            weather = response.weather?.let { mapperWeatherDescriptionList(it) }
        )
    }

    fun mapperData(response: DataResponse): DataModel {
        return DataModel(
            list = response.list?.map { mapperWeather(it) } as ArrayList<WeatherModel>
        )
    }
}