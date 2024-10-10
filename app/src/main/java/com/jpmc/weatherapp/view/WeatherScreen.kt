package com.jpmc.weatherapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jpmc.weatherapp.model.Resource
import com.jpmc.weatherapp.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(modifier: Modifier, viewModel: WeatherViewModel) {
    var city by rememberSaveable { mutableStateOf("") }
    val weatherState by viewModel.weatherState.collectAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Enter City Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (city.isNotEmpty()) {
                    viewModel.fetchWeather(city)
                }
            },
            enabled = city.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Weather")
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (weatherState) {
            is Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Success -> {
                val weather = (weatherState as Resource.Success).data
                WeatherCard(weather = weather)
            }
            is Resource.Error -> {
                Text(
                    text = "Error: ${(weatherState as Resource.Error).message}",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            else -> {
                //City Name Empty
            }
        }
    }
}