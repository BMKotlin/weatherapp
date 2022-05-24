package com.viht.weathermvvm.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.viht.weathermvvm.data.repository.ApiResult
import com.viht.weathermvvm.data.workmanager.WeatherWorkManager
import com.viht.weathermvvm.domain.model.DataModel
import com.viht.weathermvvm.domain.usecase.WeatherUseCase
import com.viht.weathermvvm.presentation.base.BaseViewModel
import com.viht.weathermvvm.presentation.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val weatherUseCase : WeatherUseCase): BaseViewModel() {
    private val _response: SingleLiveEvent<DataModel?> = SingleLiveEvent()
    val response: LiveData<DataModel?> get() = _response

    fun getListForecast(searchKey: String) {
        handleLoading(true)
        viewModelScope.launch {
            weatherUseCase.execute(searchKey).collect { values ->
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
}