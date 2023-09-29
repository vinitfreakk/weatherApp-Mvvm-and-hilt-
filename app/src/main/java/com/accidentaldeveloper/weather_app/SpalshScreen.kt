package com.accidentaldeveloper.weather_app

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class SpalshScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh_screen)


        Handler(Looper.getMainLooper()).postDelayed({
            val intent  = Intent(this@SpalshScreen,MainActivity::class.java)
            startActivity(intent)
        },3000)
    }
}