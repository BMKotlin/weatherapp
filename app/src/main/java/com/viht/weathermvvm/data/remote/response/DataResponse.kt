package com.viht.weathermvvm.data.remote.response

import android.os.Parcelable
import com.viht.weathermvvm.model.Weather
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataResponse(
    val list: ArrayList<WeatherResponse>?
) : Parcelable

fun DataResponse.toConvertedData(): List<Weather> {
    val outputList: MutableList<Weather> = mutableListOf()
    this.list?.forEach {  response ->
        outputList.add(response.toWeather())
    }
    return outputList
}