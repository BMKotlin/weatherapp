package com.viht.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.viht.presentation.R
import com.viht.data.workmanager.WeatherWorkManager
import com.viht.domain.model.Weather
import com.viht.presentation.ui.adapter.WeatherAdapter
import com.viht.presentation.base.BaseActivity
import com.viht.presentation.databinding.ActivityMainBinding
import com.viht.presentation.ui.main.MainViewModel
import com.viht.presentation.utils.getValue
import com.viht.presentation.utils.hideKeyboard
import com.viht.presentation.utils.isValidationSearchKey
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var workManager: WorkManager

    private val viewModel by viewModels<MainViewModel>()

    private var adapterWeather = WeatherAdapter {
        onClickWeather(it)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
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
                    viewModel.getListForecast(searchKey)
                }
            }
        }

    }

    private fun initData() {
        viewModel.apply {
            error.observe(this@MainActivity) {
                shortShowToast(it)
                Log.d("viht error", it)
                adapterWeather.submitList(mutableListOf())
            }

            loading.observe(this@MainActivity) {
                if (it) {
                    showProgressBar()
                } else {
                    hideProgressBar()
                }
            }

            response.observe(this@MainActivity) {
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