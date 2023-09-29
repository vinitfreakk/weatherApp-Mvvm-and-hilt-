package com.accidentaldeveloper.weather_app.api

import com.accidentaldeveloper.weather_app.helper.AppConstant
import com.accidentaldeveloper.weather_app.models.Weather_Response
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET(AppConstant.END_POINT)
    suspend fun getWeatherData(
        @Query("q")
        city:String,
        @Query("appid")
        appid:String,
       /* @Query("units")
        units:String*/


    ):Response<Weather_Response>
}