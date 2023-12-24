package com.example.beginvegan.src.ui.view.main

import android.Manifest
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivitySplashBinding
import com.example.beginvegan.src.ui.view.login.LoginActivity
import com.example.beginvegan.util.Constants


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Log.d("Build Version Check", "SDK VERSION 12 higher")
            // SDK 12
            installSplashScreen()
            splashScreen.setOnExitAnimationListener { _ ->
                Handler(mainLooper).postDelayed({
                    moveToLogin()
                }, 1000)
            }
        } else {
            Log.d("Build Version Check", "SDK VERSION 12 lower")
            Handler(mainLooper).postDelayed({
                moveToLogin()
            }, 3000)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    private fun moveToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}