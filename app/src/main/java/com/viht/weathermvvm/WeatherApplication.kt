package com.viht.weathermvvm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import androidx.work.Configuration
import com.viht.weathermvvm.data.workmanager.WeatherWorkFactory

@HiltAndroidApp
class WeatherApplication: Application() , Configuration.Provider {

    @Inject
    lateinit var weatherFactory: WeatherWorkFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(weatherFactory)
            .build()
    }
}