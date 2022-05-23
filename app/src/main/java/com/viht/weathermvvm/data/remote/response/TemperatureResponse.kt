package com.viht.weathermvvm.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TemperatureResponse(
    val min: Float?,
    val max: Float?
) : Parcelable {

    fun getAvg(): Int {
        val minNotNul: Float = min ?: 0F
        val maxNotNul: Float = max ?: 0F
        return ((minNotNul + maxNotNul) / 2).toInt()
    }
}