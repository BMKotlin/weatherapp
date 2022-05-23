package com.viht.weathermvvm.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.viht.weathermvvm.R
import com.viht.weathermvvm.data.remote.response.toConvertedData
import com.viht.weathermvvm.databinding.ActivityMainBinding
import com.viht.weathermvvm.model.Weather
import com.viht.weathermvvm.ui.adapter.WeatherAdapter
import com.viht.weathermvvm.ui.base.BaseActivity
import com.viht.weathermvvm.ui.main.MainViewModel
import com.viht.weathermvvm.utils.getValue
import com.viht.weathermvvm.utils.hideKeyboard
import com.viht.weathermvvm.utils.isValidationSearchKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

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
        binding.rvWeather.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            binding.rvWeather.context,
            layoutManager.orientation
        )
        binding.rvWeather.addItemDecoration(dividerItemDecoration)

        binding.rvWeather.adapter = adapterWeather

        binding.btnGetWeather.setOnClickListener {
            hideKeyboard()
            val searchKey = binding.edSearch.getValue()
            if (!binding.edSearch.isValidationSearchKey()) {
                Toast.makeText(this@MainActivity, resources.getString(R.string.text_check_search_key), Toast.LENGTH_SHORT).show()
            } else {
                viewModel.getListForecast(searchKey)
            }
        }
    }

    private fun initData() {
        viewModel.error.observe(this) {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            Log.d("viht error", it)
            adapterWeather.submitList(mutableListOf())
        }

        viewModel.loading.observe(this) {
            if (it) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        }

        viewModel.response.observe(this) {
            Log.d("viht", it.toString())
            adapterWeather.submitList(it?.toConvertedData())
        }
    }

    private fun onClickWeather(weather: Weather) {
        Toast.makeText(this, weather.description, Toast.LENGTH_SHORT).show()
    }
}