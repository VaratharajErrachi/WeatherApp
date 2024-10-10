package com.jpmc.weatherapp.webservices

import com.jpmc.weatherapp.model.Resource
import com.jpmc.weatherapp.model.WeatherData

class WeatherRepository {
    //Fetch weather details by city name using the common error handler
    suspend fun getWeatherDetails(cityName: String): Resource<WeatherData> {
        return ApiError {
            RetrofitClient.apiService.getWeatherDetails(cityName, ApiConfig.APP_ID)
        }
    }
}