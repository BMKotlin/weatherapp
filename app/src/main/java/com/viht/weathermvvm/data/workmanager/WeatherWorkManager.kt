package com.viht.weathermvvm.data.workmanager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.viht.weathermvvm.data.local.dao.WeatherDAO


@HiltWorker
class WeatherWorkManager constructor(
    context: Context,
    workerParameters: WorkerParameters,
    private val local: WeatherDAO
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        local.deleteAll()
        Log.d("Work for every second", "doWork: Running")
//        withContext(Dispatchers.IO) {
////            local.deleteAll()
//
//            return@withContext Result.success()
//        }
        return Result.success()
//        return Result.failure()
    }
}