package com.jpmc.weatherapp.repository

import com.jpmc.weatherapp.model.Resource
import com.jpmc.weatherapp.model.WeatherData
import com.jpmc.weatherapp.util.ApiConfig
import com.jpmc.weatherapp.webservices.ApiError
import com.jpmc.weatherapp.webservices.RetrofitClient

class WeatherRepository {
    //Fetch weather details by city name using the common error handler
    suspend fun getWeatherDetails(cityName: String): Resource<WeatherData> {
        return ApiError {
            RetrofitClient.apiService.getWeatherDetails(cityName, ApiConfig.APP_ID)
        }
    }
}