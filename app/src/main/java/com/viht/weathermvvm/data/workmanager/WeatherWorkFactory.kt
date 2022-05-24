package com.viht.weathermvvm.data.workmanager

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.viht.weathermvvm.data.local.dao.WeatherDAO
import javax.inject.Inject

class WeatherWorkFactory @Inject constructor(
    private val local: WeatherDAO
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return WeatherWorkManager(appContext, workerParameters, local)
    }
}