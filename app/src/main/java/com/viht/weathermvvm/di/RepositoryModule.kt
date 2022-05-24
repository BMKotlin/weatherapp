package com.viht.weathermvvm.di

import com.viht.data.repository.WeatherRepositoryImp
import com.viht.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(impl: WeatherRepositoryImp): WeatherRepository
}