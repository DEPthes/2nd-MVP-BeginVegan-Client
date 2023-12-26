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
import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivitySplashBinding
import com.example.beginvegan.src.data.model.auth.AuthSignInterface
import com.example.beginvegan.src.data.model.auth.AuthSignResponse
import com.example.beginvegan.src.data.model.auth.AuthSignService
import com.example.beginvegan.src.ui.view.login.LoginActivity
import com.example.beginvegan.util.Constants
import com.example.beginvegan.util.Constants.PROVIDER_ID
import com.example.beginvegan.util.Constants.USER_ID


class SplashActivity : AppCompatActivity(), AuthSignInterface {
    private var mProviderID: String? = null
    private var mEmail: String? = null
    private var isAutoLogin: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        isAutoLogin = checkLoginStatus()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Log.d("Build Version Check", "SDK VERSION 12 higher")
            installSplashScreen()
            autoLogin()
            splashScreen.setOnExitAnimationListener { _ ->
                moveHandler()
            }
        } else {
            Log.d("Build Version Check", "SDK VERSION 12 lower")
            autoLogin()
            moveHandler()
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    private fun moveToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveHandler() {
        Handler(mainLooper).postDelayed({
            if (isAutoLogin) {
                moveToMain()
            } else {
                moveToLogin()
            }
        }, 3000)

    }

    private fun autoLogin() {
        if (isAutoLogin) {
            AuthSignService(this).tryPostAuthSignIn(mProviderID!!, mEmail!!)
        } else {
            isAutoLogin = false
        }
    }

    private fun checkLoginStatus(): Boolean {
        mProviderID = ApplicationClass.sSharedPreferences.getString(PROVIDER_ID, null)
        mEmail = ApplicationClass.sSharedPreferences.getString(USER_ID, null)
        return mProviderID != null && mEmail != null
    }

    override fun onPostAuthSignInSuccess(response: AuthSignResponse) {
        isAutoLogin = true
    }

    override fun onPostAuthSignInFailed(message: String) {
        Toast.makeText(this, "사용자 정보 오류입니다", Toast.LENGTH_SHORT).show()
    }

    override fun onPostAuthSignUpSuccess(response: AuthSignResponse) {}
    override fun onPostAuthSignUpFailed(message: String) {}
}