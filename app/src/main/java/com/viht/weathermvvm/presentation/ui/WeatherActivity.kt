package com.viht.weathermvvm.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.viht.weathermvvm.R
import com.viht.weathermvvm.data.workmanager.WeatherWorkManager
import com.viht.weathermvvm.databinding.ActivityWeatherBinding
import com.viht.weathermvvm.domain.model.Weather
import com.viht.weathermvvm.presentation.ui.adapter.WeatherAdapter
import com.viht.weathermvvm.presentation.base.BaseActivity
import com.viht.weathermvvm.presentation.base.UiState
import com.viht.weathermvvm.presentation.ui.weather.WeatherViewModel
import com.viht.weathermvvm.presentation.utils.collectInViewLifecycle
import com.viht.weathermvvm.presentation.utils.getValue
import com.viht.weathermvvm.presentation.utils.hideKeyboard
import com.viht.weathermvvm.presentation.utils.isValidationSearchKey
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class WeatherActivity : BaseActivity<ActivityWeatherBinding>() {

    @Inject
    lateinit var workManager: WorkManager

    private val viewModel by viewModels<WeatherViewModel>()

    private var adapterWeather = WeatherAdapter {
        onClickWeather(it)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityWeatherBinding {
        return ActivityWeatherBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initData()
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(this)

        binding.apply {
            rvWeather.layoutManager = layoutManager

            val dividerItemDecoration = DividerItemDecoration(
                rvWeather.context,
                layoutManager.orientation
            )
            rvWeather.addItemDecoration(dividerItemDecoration)

            rvWeather.adapter = adapterWeather

            btnGetWeather.setOnClickListener {
                hideKeyboard()
                val searchKey = edSearch.getValue()
                if (!edSearch.isValidationSearchKey()) {
                    shortShowToast(resources.getString(R.string.text_check_search_key))
                } else {
                    viewModel.getListForecastStateFlowUpdate(searchKey)
                }
            }
        }

    }

    private fun initData() {
        viewModel.apply {
            uiState.collectInViewLifecycle(this@WeatherActivity) { values->
                when(values){
                    is UiState.Success -> {
                        Log.d("viht", values.data.toString())
                        adapterWeather.submitList(values.data?.toWeatherUI())
                    }
                    is UiState.Error -> {
                        shortShowToast(values.message)
                        Log.d("viht error", values.message)
                        adapterWeather.submitList(mutableListOf())
                    }
                    is UiState.Loading -> {
                        if (values.loading) {
                            showProgressBar()
                        } else {
                            hideProgressBar()
                        }
                    }
                }
            }

            error.observe(this@WeatherActivity) {
                shortShowToast(it)
                Log.d("viht error", it)
                adapterWeather.submitList(mutableListOf())
            }

            loading.observe(this@WeatherActivity) {
                if (it) {
                    showProgressBar()
                } else {
                    hideProgressBar()
                }
            }

            response.observe(this@WeatherActivity) {
                Log.d("viht", it.toString())
                adapterWeather.submitList(it?.toWeatherUI())
            }
        }

        startPeriodicWork()
    }

    private fun onClickWeather(weather: Weather) {
        shortShowToast(weather.description)
    }

    private fun startPeriodicWork() {
        CoroutineScope(Dispatchers.Default).launch {
            val constraints =
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

            val periodicWorkRequest: PeriodicWorkRequest =
                PeriodicWorkRequestBuilder<WeatherWorkManager>(
                    1, TimeUnit.DAYS
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