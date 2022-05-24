package com.viht.domain.utils

import java.text.SimpleDateFormat
import java.util.*


object DateUtil {
    private const val DATE_FORMAT = "EEEE, dd MMM yyyy"

    fun getDate(timestamp: Long): String {
        val time = Date(timestamp * 1000L)
        val sdf = SimpleDateFormat(DATE_FORMAT)
        return sdf.format(time)
    }

    fun getDateFromNow(): String{
        val time = Date()
        val sdf = SimpleDateFormat(DATE_FORMAT)
        return sdf.format(time)
    }
}