package com.example.beginvegan.src.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.beginvegan.R

class  SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)

        finish( )
    }
}