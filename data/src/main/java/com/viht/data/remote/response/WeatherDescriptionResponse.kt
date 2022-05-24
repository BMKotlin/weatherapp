package com.viht.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDescriptionResponse(
    val main: String?,
    val description: String?
) : Parcelable