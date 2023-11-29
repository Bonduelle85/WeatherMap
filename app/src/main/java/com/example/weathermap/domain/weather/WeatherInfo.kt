package com.example.weathermap.domain.weather

data class WeatherInfo(
    // Int - index is the day, for example 0 - today, 1 - tomorrow and so on.
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)
