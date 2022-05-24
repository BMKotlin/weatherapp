package com.viht.data.workmanager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.viht.data.local.dao.WeatherDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@HiltWorker
class WeatherWorkManager constructor(
    context: Context,
    workerParameters: WorkerParameters,
    private val local: WeatherDAO
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        Log.d("Work for everyday", "doWork: Running")
        withContext(Dispatchers.IO) {
            local.deleteAll()
        }
        return Result.success()
    }
}