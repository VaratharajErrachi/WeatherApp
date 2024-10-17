package com.jpmc.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpmc.weatherapp.model.Resource
import com.jpmc.weatherapp.model.WeatherData
import com.jpmc.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val repository = WeatherRepository()
    private val _weatherState = MutableStateFlow<Resource<WeatherData>?>(null)
    val weatherState: StateFlow<Resource<WeatherData>?> = _weatherState

    //Fetch weather details using City Name
    fun fetchWeather(cityName: String) {
        viewModelScope.launch {
            _weatherState.value = Resource.Loading
            val result = repository.getWeatherDetails(cityName)
            _weatherState.value = result
        }
    }
}