package com.accidentaldeveloper.weather_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.accidentaldeveloper.weather_app.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.GlobalScope


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewmodel:WeatherViewModel by viewModels()

    private lateinit var searchView: SearchView
    private lateinit var Weather_img:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txt = findViewById<TextView>(R.id.tv)
        val temp = findViewById<TextView>(R.id.temp)
        val temp_txt = findViewById<TextView>(R.id.temp_txt)
        val wind_speed = findViewById<TextView>(R.id.wind_speed)
        Weather_img = findViewById<ImageView>(R.id.imageView);

       searchView = findViewById(R.id.searchView)

        viewmodel.fetchWeather("bengaluru")
        // Set up a QueryTextListener to listen for text changes in the search view
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewmodel.fetchWeather(query.toString())
                searchView.setQuery("", false)
                searchView.queryHint = "Enter City"
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
        })








        viewmodel.live_Weather_Response.observe(this,{
            val kelvin = it.main.temp.toDouble() // Convert to Double if necessary
            val celsius = (kelvin - 273).toInt()
            txt.setText(it.name)
            temp.setText(celsius.toString())
            temp_txt.setText(it.weather.get(0).description)
            wind_speed.setText(it.wind.speed.toString())
            setWheatherIcon(it.weather.get(0).description)
            Log.d("TAG", "onCreate: ${it.main}")
        })


    }
    private fun setWheatherIcon(wheather:String){
        when(wheather){
            "clear sky"->Weather_img.setImageResource(R.drawable.sun)
            "few clouds"->Weather_img.setImageResource(R.drawable.few_clouds)
            "scattered clouds"->Weather_img.setImageResource(R.drawable.scattered_clouds)
            "broken clouds"->Weather_img.setImageResource(R.drawable.few_clouds)
            "shower rain"->Weather_img.setImageResource(R.drawable.shower_rain)
            "rain"->Weather_img.setImageResource(R.drawable.rain)
            "thunderstorm"->Weather_img.setImageResource(R.drawable.thunderstorm)
            "snow"->Weather_img.setImageResource(R.drawable.snow)
            "mist"->Weather_img.setImageResource(R.drawable.mist)
            "overcast clouds"->Weather_img.setImageResource(R.drawable.dark_cloud)
            "haze"->Weather_img.setImageResource(R.drawable.dust)
        }
    }
}