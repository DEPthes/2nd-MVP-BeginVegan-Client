package com.example.beginvegan.src.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
    private  val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e("KaKao Login | CallBack", "로그인 실패 $error")
            Toast.makeText(this,"카카오 로그인 실패",Toast.LENGTH_SHORT).show()
        } else if (token != null) {
            Log.e("KaKao Login | CallBack", "로그인 성공 ${token.accessToken}")
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun init() {

        binding.btnLoginKakao.setOnClickListener{
            // 이메일 로그인 콜백
// 카카오톡 설치 확인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                // 카카오톡 로그인
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    // 로그인 실패 부분
                    if (error != null) {
                        Log.e("KaKao Login", "로그인 실패 $error")
                        // 사용자가 취소
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled ) {
                            Log.e("KaKao Login", "로그인 실패 사용자 취소")
                            return@loginWithKakaoTalk
                        }
                        // 다른 오류
                        else {
                            UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback) // 카카오 이메일 로그인
                            Log.e("KaKao Login", "예외 오류")
                        }
                    }
                    // 로그인 성공 부분
                    else if (token != null) {
                        Log.e("KaKao Login", "로그인 성공 ${token.accessToken}")
                    }
                }
            } else {
                Log.e("KaKao Login", "이메일 로그인")
                UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback) // 카카오 이메일 로그인
            }
        }
    }
}