package com.jpmc.weatherapp.webservices

import com.jpmc.weatherapp.model.WeatherData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(ApiConfig.WEATHER_URL)
    suspend fun getWeatherDetails(@Query(ApiConfig.QUERY_CITY_NAME) cityName: String, @Query(ApiConfig.QUERY_APP_ID) appId: String): WeatherData
}

object RetrofitClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}