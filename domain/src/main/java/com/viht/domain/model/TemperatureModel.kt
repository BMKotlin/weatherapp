package com.viht.domain.model

data class TemperatureModel(
    val min: Float?,
    val max: Float?
) {

    fun getAvg(): Int {
        val minNotNul: Float = min ?: 0F
        val maxNotNul: Float = max ?: 0F
        return ((minNotNul + maxNotNul) / 2).toInt()
    }
}