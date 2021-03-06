package com.viht.weathermvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.viht.weathermvvm.data.remote.response.DataResponse
import com.viht.weathermvvm.data.workmanager.WeatherWorkManager
import com.viht.weathermvvm.repository.ApiResult
import com.viht.weathermvvm.repository.WeatherRepository
import com.viht.weathermvvm.ui.base.BaseViewModel
import com.viht.weathermvvm.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository, private val workManager: WorkManager): BaseViewModel() {
    private val _response: SingleLiveEvent<DataResponse?> = SingleLiveEvent()
    val response: LiveData<DataResponse?> get() = _response

    fun getListForecast(searchKey: String) {
        handleLoading(true)
        viewModelScope.launch {
            repository.getListForecast(searchKey).collect { values ->
                when(values){
                    is ApiResult.Success -> {
                        _response.postValue(values.data)
                        handleLoading(false)
                    }
                    is ApiResult.Error -> {
                        handleError(values.message ?: "")
                        handleLoading(false)
                    }
                    is ApiResult.Loading -> {
                        handleLoading(true)
                    }
                }
            }
        }
    }

    fun startPeriodicWork() {
        CoroutineScope(Dispatchers.Default).launch {

            val constraints =
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

            val periodicWorkRequest: PeriodicWorkRequest =
                PeriodicWorkRequestBuilder<WeatherWorkManager>(
                    15, TimeUnit.MINUTES
                )
                    .addTag("DELETE_WEATHER_WORKER")
                    //.setConstraints(constraints)
                    .build()

            workManager.enqueueUniquePeriodicWork(
                "DELETE_WEATHER",
                ExistingPeriodicWorkPolicy.REPLACE,
                periodicWorkRequest
            )
        }
    }
}