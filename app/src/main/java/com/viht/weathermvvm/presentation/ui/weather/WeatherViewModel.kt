package com.viht.weathermvvm.presentation.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.viht.weathermvvm.data.repository.ApiResult
import com.viht.weathermvvm.domain.model.DataModel
import com.viht.weathermvvm.domain.usecase.WeatherUseCase
import com.viht.weathermvvm.presentation.base.BaseViewModel
import com.viht.weathermvvm.presentation.base.UiState
import com.viht.weathermvvm.presentation.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherUseCase : WeatherUseCase): BaseViewModel() {
    private val _response: SingleLiveEvent<DataModel?> = SingleLiveEvent()
    val response: LiveData<DataModel?> get() = _response

    private val _uiState = MutableStateFlow<UiState<DataModel>>(UiState.Loading(false))

    val uiState: StateFlow<UiState<DataModel>> = _uiState

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

    fun getListForecastStateFlow(searchKey: String) {
        viewModelScope.launch {
            _uiState.emit(UiState.Loading(true))
            weatherUseCase(searchKey).collect { values ->
                when(values){
                    is ApiResult.Success -> {
                        _uiState.emit( UiState.Success(values.data))
                        _uiState.emit(UiState.Loading(false))
                    }
                    is ApiResult.Error -> {
                        _uiState.emit(UiState.Error(values.message ?: ""))
                        _uiState.emit(UiState.Loading(false))
                    }
                    is ApiResult.Loading -> {
                        _uiState.emit(UiState.Loading(true))
                    }
                }
            }
        }
    }

    fun getListForecastStateFlowUpdate(searchKey: String) {
        viewModelScope.launch {
            _uiState.emit(UiState.Loading(true))
            weatherUseCase(searchKey).collect { values ->
                _uiState.update {
                    when (values) {
                        is ApiResult.Success -> {
                            UiState.Success(values.data)
                            UiState.Loading(false)
                        }

                        is ApiResult.Error -> {
                            UiState.Error(values.message ?: "")
                            UiState.Loading(false)
                        }

                        is ApiResult.Loading -> {
                            UiState.Loading(true)
                        }

                        null -> TODO()
                    }
                }
            }
        }
    }
}