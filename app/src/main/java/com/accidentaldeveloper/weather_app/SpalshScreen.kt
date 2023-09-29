package com.accidentaldeveloper.weather_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class SpalshScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh_screen)
 /*       val videoView:VideoView = findViewById(R.id.video)
        val videoPAth = "android.resource://"+ packageName + "/" + R.raw.clouds
        val videoUri = Uri.parse(videoPAth)
        videoView.setVideoURI(videoUri)

        videoView.start()

        videoView.setOnCompletionListener {
            val intent  = Intent(this@SpalshScreen,MainActivity::class.java)
            startActivity(intent)
            finish()
        }*/

        Handler(Looper.getMainLooper()).postDelayed({
            val intent  = Intent(this@SpalshScreen,MainActivity::class.java)
            startActivity(intent)
        },3000)
    }
}