package com.viht.weathermvvm.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataResponse(
    val list: ArrayList<WeatherResponse>?
) : Parcelable