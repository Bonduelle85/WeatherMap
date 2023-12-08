package com.example.weathermap.domain.repository

import com.example.weathermap.domain.util.Resource
import com.example.weathermap.domain.weather.WeatherInfo


interface WeatherRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherInfo>
}