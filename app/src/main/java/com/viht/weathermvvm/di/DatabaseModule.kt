package com.viht.weathermvvm.di

import android.content.Context
import androidx.work.WorkManager
import com.viht.weathermvvm.data.local.WeatherDatabase
import com.viht.weathermvvm.data.local.dao.WeatherDAO
import com.viht.weathermvvm.data.workmanager.WeatherWorkFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return WeatherDatabase.getInstance(context)
    }

    @Provides
    fun provideWeatherDao(appDatabase: WeatherDatabase): WeatherDAO {
        return appDatabase.getWeatherDao()
    }

    @Singleton
    @Provides
    fun provideWorkerFactory(
        local: WeatherDAO
    ) = WeatherWorkFactory(local)
}