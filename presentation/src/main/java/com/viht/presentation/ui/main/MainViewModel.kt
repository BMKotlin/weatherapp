package com.viht.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.viht.domain.model.DataModel
import com.viht.domain.repository.ApiResult
import com.viht.presentation.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val weatherUseCase : com.viht.domain.usecase.WeatherUseCase): com.viht.presentation.base.BaseViewModel() {
    private val _response: SingleLiveEvent<DataModel?> = SingleLiveEvent()
    val response: LiveData<com.viht.domain.model.DataModel?> get() = _response

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