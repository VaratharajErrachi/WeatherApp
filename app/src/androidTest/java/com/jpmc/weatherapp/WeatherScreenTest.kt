package com.jpmc.weatherapp

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.jpmc.weatherapp.view.WeatherScreen
import com.jpmc.weatherapp.viewmodel.WeatherViewModel
import org.junit.Rule
import org.junit.Test

class WeatherScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testTextFieldLabel() {
        getWeatherScreen()

        //Assert that the Text Field label is displayed
        composeRule.onNodeWithText("Enter City Name").assertIsDisplayed()
    }

    @Test
    fun testButtonText() {
        getWeatherScreen()

        //Assert that the Button Text is displayed
        composeRule.onNodeWithText("Get Weather").assertIsDisplayed()
    }

    @Test
    fun testWeatherCardView() {
        getWeatherScreen()

        composeRule.onNodeWithTag("WeatherCard").assertDoesNotExist()
    }

    private fun getWeatherScreen() {
        composeRule.setContent {
            WeatherScreen(Modifier.padding(), WeatherViewModel())
        }
    }
}