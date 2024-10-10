package com.jpmc.weatherapp.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jpmc.weatherapp.model.WeatherData
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.jpmc.weatherapp.webservices.ApiConfig

@Composable
fun WeatherCard(weather: WeatherData) {
    val imageUrl = ApiConfig.ICON_URL + weather.weather.firstOrNull()?.icon + "@2x.png"
    Log.d("1234", imageUrl)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = // Optional: apply transformations
                rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = imageUrl)
                        .apply(block = fun ImageRequest.Builder.() {
                            transformations(CircleCropTransformation()) // Optional: apply transformations
                        }).build()
                ),
                contentDescription = "Loaded Image",
                modifier = Modifier
                    .size(72.dp) // Set size
                    .clip(CircleShape),
                contentScale = ContentScale.Fit// Optional: clip to circle
            )

            // Display city name
            Text(
                text = weather.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Display temperature
            Text(
                text = "Temperature: ${weather.main.temp} °C",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Display humidity
            Text(
                text = "Humidity: ${weather.main.humidity}%",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Display weather description
            Text(
                text = weather.weather.firstOrNull()?.description ?: "No description",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}