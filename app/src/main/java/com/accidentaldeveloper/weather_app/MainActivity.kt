package com.accidentaldeveloper.weather_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.accidentaldeveloper.weather_app.api.ApiServices
import com.accidentaldeveloper.weather_app.helper.AppConstant
import com.accidentaldeveloper.weather_app.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewmodel:WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txt = findViewById<TextView>(R.id.tv)

       /* val retrofit = Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)

          lifecycleScope.launch {
              Log.d("TAG", "onCreate:${retrofit.getWeatherData("belgaum","00da95dc79d1f6924673a9e8261bdf09").body()!!.sys}")
          }*/

        viewmodel.fetchWeather("delhi")

        viewmodel.live_Weather_Response.observe(this,{
            txt.setText(it.name)
            Log.d("TAG", "onCreate: ${it.main}")
        })
    }
}