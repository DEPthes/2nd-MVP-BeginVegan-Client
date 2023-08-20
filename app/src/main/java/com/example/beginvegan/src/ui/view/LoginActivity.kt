package com.example.beginvegan.src.ui.view

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivityLoginBinding
import com.example.beginvegan.src.data.model.auth.Auth
import com.example.beginvegan.src.data.model.auth.AuthLoginResponse
import com.example.beginvegan.src.data.model.auth.AuthResponse
import com.example.beginvegan.src.data.model.auth.AuthSignInterface
import com.example.beginvegan.src.data.model.auth.AuthSignService
import com.example.beginvegan.src.data.model.user.UserCheckInterface
import com.example.beginvegan.src.data.model.user.UserCheckService
import com.example.beginvegan.src.data.model.user.UserResponse
import com.example.beginvegan.util.Constants.PROVIDER_ID
import com.example.beginvegan.util.Constants.USER_EMAIL
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class LoginActivity : BaseActivity<ActivityLoginBinding>({ActivityLoginBinding.inflate(it)}),AuthSignInterface,UserCheckInterface{
    private lateinit var mAuth: Auth

    private  val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e("KaKao Login | CallBack", "로그인 실패 $error")
            Toast.makeText(this,"카카오 로그인 실패",Toast.LENGTH_SHORT).show()
        } else if (token != null) {
            Log.e("KaKao Login | CallBack", "로그인 성공 ${token.accessToken}")
            showLoadingDialog(this)
            getKakaoUserData()

        }
    }


    override fun init() {
        binding.btnLoginKakao.setOnClickListener{
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
                        showLoadingDialog(this)
                        getKakaoUserData()
                    }
                }
            } else {
                Log.e("KaKao Login", "이메일 로그인")
                UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback) // 카카오 이메일 로그인
            }
        }
    }
    private fun getKakaoUserData(){
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("KaKao User", "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                Log.i("KaKao User", "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                mAuth = Auth(
                    (user.id).toString()!!,
                    user.kakaoAccount?.profile?.nickname!!,
                    user.kakaoAccount?.email!!,
                    user.kakaoAccount?.profile?.thumbnailImageUrl!!)
                AuthSignService(this).tryPostAuthSignIn(mAuth)
            }
        }
    }
    private fun moveToWelcome(){
        val intent = Intent(this,WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun setUserData(response: AuthResponse){
        // 자동 로그인을 위한 유저 로그인 정보 저장
        ApplicationClass.sSharedPreferences.edit().putString(PROVIDER_ID,mAuth.providerId).apply()
        ApplicationClass.sSharedPreferences.edit().putString(USER_EMAIL,mAuth.email).apply()
        // 싱글톤 토큰 / 유저 정보 기입
        ApplicationClass.xAccessToken = "${response.tokenType} ${response.accessToken}"
        ApplicationClass.xRefreshToken = response.refreshToken
        Log.d("setUserData xAccessToken",ApplicationClass.xAccessToken)
        Log.d("setUserData xRefreshToken",ApplicationClass.xRefreshToken)

        UserCheckService(this).tryGetUser()
    }

    override fun onPostAuthSignInSuccess(response: AuthLoginResponse) {
        Log.d("onPostAuthSignInSuccess",response.toString())
        setUserData(response.information)
    }

    override fun onPostAuthSignInFailed(message: String) {
        Log.d("onPostAuthSignInFailed",message)
        AuthSignService(this).tryPostAuthSignUp(mAuth)
    }

    override fun onPostAuthSignUpSuccess(response: AuthResponse) {
        Log.d("onPostAuthSignUpSuccess",response.toString())
        setUserData(response)
    }

    override fun onPostAuthSignUpFailed(message: String) {
        Log.d("onPostAuthSignUpFailed",message)
        dismissLoadingDialog()
    }



    override fun onGetUserSuccess(response: UserResponse) {
        Log.d("onGetUserSuccess",response.toString())
        ApplicationClass.xAuth = Auth(mAuth.providerId,mAuth.email,response.name,response.imageUrl)
        dismissLoadingDialog()
        moveToWelcome()
    }

    override fun onGetUserFailure(message: String) {
        Log.d("onGetUserFailure",message)
        dismissLoadingDialog()
    }

}