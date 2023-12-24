package com.example.beginvegan.src.ui.view.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Handler
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivitySplashBinding
import com.example.beginvegan.src.ui.view.login.LoginActivity
import com.example.beginvegan.util.Constants


class  SplashActivity : BaseActivity<ActivitySplashBinding>({ActivitySplashBinding.inflate(it)}){
    override fun init() {

    }
    // 자동 로그인 추가
    private fun moveToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}