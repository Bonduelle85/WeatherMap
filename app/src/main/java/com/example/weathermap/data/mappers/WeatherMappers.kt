package com.example.weathermap.data.mappers

import com.example.weathermap.data.remote.WeatherDataDto
import com.example.weathermap.data.remote.WeatherDto
import com.example.weathermap.domain.weather.WeatherData
import com.example.weathermap.domain.weather.WeatherInfo
import com.example.weathermap.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// The Map does not contain indexes, so we create a private data class IndexedWeatherData.
private data class IndexedWeatherData(
    val index: Int,
    val weatherData: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return times.mapIndexed { index: Int, time: String ->
        val temperature = temperatures[index]
        val pressure = pressures[index]
        val windSpeed = windSpeeds[index]
        val humidity = humidities[index]
        val weatherCode = weatherCodes[index]
        IndexedWeatherData(
            index = index,
            weatherData = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperature = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy { indexedWeatherData ->
        // Each "index/24" (24 indexes per day in Json) will correspond to a day: 0 - today, 1 - tomorrow, etc.
        indexedWeatherData.index / 24
    }.mapValues { entry ->
        entry.value.map { indexedWeatherData ->
            indexedWeatherData.weatherData
        }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val currentTime = LocalDateTime.now()

    val currentWeatherData =
        if (currentTime.hour < 23) {
            weatherDataMap[0]?.find { weatherData ->
                val hour = if (currentTime.minute > 30) currentTime.hour else currentTime.hour + 1
                weatherData.time.hour == hour
            }
        } else{
            weatherDataMap[1]?.get(0)
            }

    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}