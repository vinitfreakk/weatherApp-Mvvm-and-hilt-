package com.accidentaldeveloper.weather_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accidentaldeveloper.weather_app.helper.AppConstant
import com.accidentaldeveloper.weather_app.models.Weather_Response
import com.accidentaldeveloper.weather_app.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(val weatherRepository: WeatherRepository,val name:String):ViewModel() {
    private val Weather_Response = MutableLiveData<Weather_Response>()
    val live_Weather_Response:LiveData<Weather_Response> = Weather_Response

    init{
        fetchWeather(name)
    }

    fun fetchWeather(city:String) = viewModelScope.launch(Dispatchers.IO) {
      weatherRepository.getWeatherForecast(city,AppConstant.api_key,"metrics").let {response->
           if(response.isSuccessful){
               Weather_Response.postValue(response.body())
           }else{
               Log.d("tag","getAllTvShows Error:${response.code()}")
           }
      }
   }


}