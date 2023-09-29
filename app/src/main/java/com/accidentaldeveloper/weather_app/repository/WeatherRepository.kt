package com.accidentaldeveloper.weather_app.repository

import com.accidentaldeveloper.weather_app.api.ApiServices
import javax.inject.Inject

class WeatherRepository @Inject constructor(val apiServices: ApiServices) {
    suspend fun getWeatherForecast(query: String,appid:String) = apiServices.getWeatherData(query,appid)
}