package com.viht.weathermvvm.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDescriptionModel(
    val main: String?,
    val description: String?
) : Parcelable