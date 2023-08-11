package com.example.beginvegan.src.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class LoginActivity : BaseActivity<ActivityLoginBinding>({ActivityLoginBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e("KaKao Login", "로그인 실패 $error")
        } else if (token != null) {
            Log.e("KaKao Login", "로그인 성공 ${token.accessToken}")
        }
    }
    override fun init() {
        binding.btnLoginKakao.setOnClickListener {
//            kakaoLogin()
            TestLogin()
        }
    }
    private fun TestLogin(){
        val intent = Intent(this,WelcomeActivity::class.java)
        startActivity(intent)
    }
    private fun kakaoLogin(){
        KakaoSdk.init(this, "fb5550ba69a57eb73aae78bc996d8a8e")
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            // 카카오톡 로그인
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                // 로그인 실패 부분
                if (error != null) {
                    Log.e("KaKao Login", "로그인 실패 $error")
                    // 사용자가 취소
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled ) {
                        return@loginWithKakaoTalk
                    }
                    // 다른 오류
                    else {
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback) // 카카오 이메일 로그인
                    }
                }
                // 로그인 성공 부분
                else if (token != null) {
                    Log.e("KaKao Login", "로그인 성공 ${token.accessToken}")
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback) // 카카오 이메일 로그인
        }
    }
}