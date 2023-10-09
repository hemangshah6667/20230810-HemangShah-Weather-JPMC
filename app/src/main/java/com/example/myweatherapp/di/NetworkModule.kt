package com.example.myweatherapp.di

import com.example.myweatherapp.data.model.WeatherService
import com.example.myweatherapp.data.repository.WeatherRepository
import com.example.myweatherapp.domain.IWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    companion object {
        @Provides
        fun provideAnalyticsService(): WeatherService {
            return Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")/*This URL will be part of build.gradle based on Enviornment*/
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService::class.java)
        }
    }

    @Binds
    abstract fun provideWeatherRepository(weatherRepository: WeatherRepository): IWeatherRepository

}