package com.example.beginvegan.src.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivitySplashBinding

class  SplashActivity : BaseActivity<ActivitySplashBinding>({ActivitySplashBinding.inflate(it)}){
    override fun init() {
        Handler(mainLooper).postDelayed({
            moveToLogin()

        },3000)
    }
    private fun moveToLogin(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}