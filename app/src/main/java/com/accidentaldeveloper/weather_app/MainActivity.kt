package com.accidentaldeveloper.weather_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import com.accidentaldeveloper.weather_app.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

import androidx.appcompat.widget.SearchView
import com.accidentaldeveloper.weather_app.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewmodel:WeatherViewModel by viewModels()

    private lateinit var searchView: SearchView
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /*val txt = binding.tv
        val temp = findViewById<TextView>(R.id.temp)
        val temp_txt = findViewById<TextView>(R.id.temp_txt)
        val wind_speed = findViewById<TextView>(R.id.wind_speed)
        Weather_img = findViewById<ImageView>(R.id.imageView);*/

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
            binding.tv.setText(it.name)
            binding.temp.setText(celsius.toString())
            binding.tempTxt.setText(it.weather.get(0).description)
            binding.windSpeed.setText(it.wind.speed.toString())
            setWheatherIcon(it.weather.get(0).description)
            Log.d("TAG", "onCreate: ${it.main}")
        })


    }
    private fun setWheatherIcon(wheather:String){
        when(wheather){
            "clear sky"->binding.imageView.setImageResource(R.drawable.sun)
            "few clouds"->binding.imageView.setImageResource(R.drawable.few_clouds)
            "scattered clouds"->binding.imageView.setImageResource(R.drawable.scattered_clouds)
            "broken clouds"->binding.imageView.setImageResource(R.drawable.few_clouds)
            "shower rain"->binding.imageView.setImageResource(R.drawable.shower_rain)
            "rain"->binding.imageView.setImageResource(R.drawable.rain)
            "thunderstorm"->binding.imageView.setImageResource(R.drawable.thunderstorm)
            "snow"->binding.imageView.setImageResource(R.drawable.snow)
            "mist"->binding.imageView.setImageResource(R.drawable.mist)
            "overcast clouds"->binding.imageView.setImageResource(R.drawable.dark_cloud)
            "haze"->binding.imageView.setImageResource(R.drawable.dust)
        }
    }
}