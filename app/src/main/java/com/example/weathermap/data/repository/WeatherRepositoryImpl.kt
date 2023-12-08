package com.example.weathermap.data.repository

import com.example.weathermap.data.mappers.toWeatherInfo
import com.example.weathermap.data.remote.WeatherApi
import com.example.weathermap.domain.repository.WeatherRepository
import com.example.weathermap.domain.util.Resource
import com.example.weathermap.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    latitude = latitude,
                    longitude = longitude
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Error occurred")
        }
    }
}