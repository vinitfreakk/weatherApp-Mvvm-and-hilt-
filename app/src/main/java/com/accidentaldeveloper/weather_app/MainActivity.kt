package com.accidentaldeveloper.weather_app

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.accidentaldeveloper.weather_app.databinding.ActivityMainBinding
import com.accidentaldeveloper.weather_app.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewmodel:WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)






        viewmodel.fetchWeather("bengaluru")
        // Set up a QueryTextListener to listen for text changes in the search view
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewmodel.fetchWeather(query.toString())
                binding.searchView.setQuery("", false)
                binding. searchView.queryHint = "Enter City"
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
            binding.temp.setText(celsius.toString()+"\u00B0"+"C")
            binding.tempTxt.setText(it.weather.get(0).description)
            binding.windSpeed.setText(it.wind.speed.toString())
            binding.sunriseDigit.setText(it.sys.sunrise.toString())
            binding.sunsetDigit.setText(it.sys.sunset.toString())
            binding.humidityDigit.setText(it.main.humidity.toString())
            binding.tempMaxDigit.setText(it.main.tempMax.toString())
            binding.tempMinDigit.setText(it.main.tempMin.toString())
            binding.visibilityDigit.setText(it.visibility.toString())
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

   /* private fun scheduleBackgroundChange() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 15) // 6 PM
        calendar.set(Calendar.MINUTE, 45)
        calendar.set(Calendar.SECOND, 0)

        val currentTime = Calendar.getInstance()

        val delayMillis: Long
        if (calendar.timeInMillis >= currentTime.timeInMillis) {
            // If it's already past 6 PM today, schedule it for 6 PM tomorrow
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        delayMillis = calendar.timeInMillis - currentTime.timeInMillis

        handler.postDelayed({
            // Execute the code to change the background color at 6 PM
             binding.background.setBackgroundColor(Color.parseColor("##FF000000"))

            // Schedule the next background change
            scheduleBackgroundChange()
        }, delayMillis)
    }*/

}