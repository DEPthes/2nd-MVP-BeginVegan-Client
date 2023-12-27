package com.example.beginvegan.src.ui.view.main

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.beginvegan.R
import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.src.data.model.auth.AuthSignInterface
import com.example.beginvegan.src.data.model.auth.AuthSignResponse
import com.example.beginvegan.src.data.model.auth.AuthSignService
import com.example.beginvegan.src.ui.view.login.LoginActivity
import com.example.beginvegan.util.Constants.ACCESS_TOKEN
import com.example.beginvegan.util.Constants.PROVIDER_ID
import com.example.beginvegan.util.Constants.USER_EMAIL
import com.example.beginvegan.util.Constants.USER_ID
import java.lang.ref.WeakReference

// 스플래쉬 보여주기 이전에 자동로그인 여부 체크
//
class SplashActivity : AppCompatActivity(), AuthSignInterface {
    private var mProviderID: String? = null
    private var mEmail: String? = null
    private var isAutoLogin: Boolean = false
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        isAutoLogin = checkLoginStatus()

        if (isVersionSOrHigher()) {
            splashScreen.setKeepOnScreenCondition{true}
        }
        if(isAutoLogin){
            AuthSignService(this).tryPostAuthSignIn(mProviderID!!,mEmail!!)
        }else{
            delayForSplashScreen { moveToLogin() }
        }
    }

    private fun checkLoginStatus(): Boolean {
        mProviderID = ApplicationClass.sSharedPreferences.getString(PROVIDER_ID, null)
        mEmail = ApplicationClass.sSharedPreferences.getString(USER_EMAIL, null)
        return mProviderID != null && mEmail != null
    }

    private fun isVersionSOrHigher() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    private fun delayForSplashScreen(action: () -> Unit) {
        handler.postDelayed({
            run(action)
        }, 1000)
    }

    private fun moveToMain() =
        startActivity(Intent(this, MainActivity::class.java)).apply {
            finish()
        }
    private fun moveToLogin() =
        startActivity(Intent(this,LoginActivity::class.java)).apply{
            finish()
        }

    override fun onPostAuthSignInSuccess(response: AuthSignResponse) {
        // 싱글톤 토큰 / 유저 정보 기입
        ApplicationClass.xAccessToken = "${response.information.tokenType} ${response.information.accessToken}"
        ApplicationClass.xRefreshToken = response.information.refreshToken

        delayForSplashScreen { moveToMain() }
    }

    override fun onPostAuthSignInFailed(message: String) {
        delayForSplashScreen { moveToLogin() }
        Toast.makeText(this, "사용자 정보 오류 입니다.", Toast.LENGTH_SHORT).show()
    }

    override fun onPostAuthSignUpSuccess(response: AuthSignResponse) {}
    override fun onPostAuthSignUpFailed(message: String) {}
}