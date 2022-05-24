package com.viht.weathermvvm.di

import com.viht.weathermvvm.domain.repository.WeatherRepository
import com.viht.weathermvvm.domain.usecase.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideWeatherUseCase(repository: WeatherRepository) = WeatherUseCase(repository)

}