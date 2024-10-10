package com.jpmc.weatherapp.model

data class WeatherData(
    val weather: List<Weather>,
    val main: Main,
    val name: String
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Float,
    val humidity: Int
)
