package com.accidentaldeveloper.weather_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.accidentaldeveloper.weather_app.models.Main
import com.accidentaldeveloper.weather_app.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewmodel:WeatherViewModel by viewModels()
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txt = findViewById<TextView>(R.id.tv)
        val temp = findViewById<TextView>(R.id.temp)
        val temp_txt = findViewById<TextView>(R.id.temp_txt)
        val wind_speed = findViewById<TextView>(R.id.wind_speed)




       /* val retrofit = Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)

          lifecycleScope.launch {
              Log.d("TAG", "onCreate:${retrofit.getWeatherData("belgaum","00da95dc79d1f6924673a9e8261bdf09").body()!!.sys}")
          }*/

        viewmodel.fetchWeather("Belgaum")

        viewmodel.live_Weather_Response.observe(this,{
            val kelvin = it.main.temp.toDouble() // Convert to Double if necessary
            val celsius = (kelvin - 273).toInt()

            txt.setText(it.name)
            temp.setText(celsius.toString())
            temp_txt.setText(it.weather.get(0).description)
            wind_speed.setText(it.wind.speed.toString())
            Log.d("TAG", "onCreate: ${it.main}")
        })


    }
}