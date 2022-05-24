package com.viht.weathermvvm.di

import android.content.Context
import com.viht.data.local.WeatherDatabase
import com.viht.data.local.dao.WeatherDAO
import com.viht.data.workmanager.WeatherWorkFactory
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
    fun provideWeatherDatabase(@ApplicationContext context: Context): com.viht.data.local.WeatherDatabase {
        return com.viht.data.local.WeatherDatabase.getInstance(context)
    }

    @Provides
    fun provideWeatherDao(appDatabase: com.viht.data.local.WeatherDatabase): com.viht.data.local.dao.WeatherDAO {
        return appDatabase.getWeatherDao()
    }

    @Singleton
    @Provides
    fun provideWorkerFactory(
        local: com.viht.data.local.dao.WeatherDAO
    ) = com.viht.data.workmanager.WeatherWorkFactory(local)
}