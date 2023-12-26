package com.example.beginvegan.src.ui.view.login

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivityLoginBinding
import com.example.beginvegan.src.data.model.auth.Auth
import com.example.beginvegan.src.data.model.auth.KakaoAuth
import com.example.beginvegan.src.data.model.auth.AuthSignResponse
import com.example.beginvegan.src.data.model.auth.AuthResponse
import com.example.beginvegan.src.data.model.auth.AuthSignInterface
import com.example.beginvegan.src.data.model.auth.AuthSignService
import com.example.beginvegan.src.data.model.user.UserCheckInterface
import com.example.beginvegan.src.data.model.user.UserCheckService
import com.example.beginvegan.src.data.model.user.UserResponse
import com.example.beginvegan.src.ui.view.main.WelcomeActivity
import com.example.beginvegan.src.ui.view.test.VeganTestActivity
import com.example.beginvegan.util.Constants
import com.example.beginvegan.util.Constants.PROVIDER_ID
import com.example.beginvegan.util.Constants.USER_EMAIL
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class LoginActivity : BaseActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it) }),
    AuthSignInterface, UserCheckInterface {
    private lateinit var mAuth: KakaoAuth
    private var creator: Boolean = true
    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e("KaKao Login | CallBack", "로그인 실패 $error")
            Toast.makeText(this, "카카오 로그인 실패", Toast.LENGTH_SHORT).show()
        } else if (token != null) {
            Log.e("KaKao Login | CallBack", "로그인 성공 ${token.accessToken}")
            showLoadingDialog(this)
            getKakaoUserData()
        }
    }


    override fun init() {
        checkPermission()

        binding.btnLoginKakao.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                // 카카오톡 로그인
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    // 로그인 실패 부분
                    if (error != null) {
                        Log.e("KaKao Login", "로그인 실패 $error")
                        // 사용자가 취소
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            Log.e("KaKao Login", "로그인 실패 사용자 취소")
                            return@loginWithKakaoTalk
                        }
                        // 다른 오류
                        else {
                            UserApiClient.instance.loginWithKakaoAccount(
                                this,
                                callback = mCallback
                            ) // 카카오 이메일 로그인
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
                UserApiClient.instance.loginWithKakaoAccount(
                    this,
                    callback = mCallback
                ) // 카카오 이메일 로그인
            }
        }
    }
    private fun checkPermission(){
        if (checkLocationService()) {
            permissionCheck()
        } else {
            Toast.makeText(this, "GPS를 켜주세요", Toast.LENGTH_SHORT).show()
        }
    }
    private fun permissionCheck() {
        val preference = this.getPreferences(MODE_PRIVATE)
        val isFirstCheck = preference.getBoolean("isFirstPermissionCheck", true)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 권한이 없는 상태
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                // 권한 거절
                val builder = AlertDialog.Builder(this)
                builder.setMessage("현재 위치를 확인하시려면 위치 권한을 허용해주세요.")
                builder.setPositiveButton("확인") { dialog, which ->
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        Constants.ACCESS_FINE_LOCATION
                    )
                }
                builder.setNegativeButton("취소") { dialog, which ->

                }
                builder.show()
            } else {
                if (isFirstCheck) {
                    // 최초 권한 요청
                    preference.edit().putBoolean("isFirstPermissionCheck", false).apply()
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        Constants.ACCESS_FINE_LOCATION
                    )
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage("현재 위치를 확인하시려면 설정에서 위치 권한을 허용해주세요.")
                    builder.setPositiveButton("설정으로 이동") { dialog, which ->
                        val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:${this.packageName}")
                        )
                        startActivity(intent)
                    }
                    builder.setNegativeButton("취소") { dialog, which ->

                    }
                    builder.show()
                }
            }
        } else {
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.ACCESS_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "위치 권한이 승인되었습니다", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "위치 권한이 거절되었습니다", Toast.LENGTH_SHORT).show()
                permissionCheck()
            }
        }
    }
    private fun checkLocationService(): Boolean {
        val locationManager =
            this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
    private fun getKakaoUserData() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("KaKao User", "사용자 정보 요청 실패", error)
            } else if (user != null) {
                Log.i(
                    "KaKao User", "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                )
                mAuth = KakaoAuth(
                    (user.id).toString()!!,
                    user.kakaoAccount?.profile?.nickname!!,
                    user.kakaoAccount?.email!!,
                    user.kakaoAccount?.profile?.thumbnailImageUrl!!
                )
                AuthSignService(this).tryPostAuthSignIn(mAuth)
            }
        }
    }

    private fun moveToWelcomeOrTest() {
        val intent: Intent = if (creator) {
            Intent(this, VeganTestActivity::class.java)
        } else {
            Intent(this, WelcomeActivity::class.java)
        }
        startActivity(intent)
        finish()
    }

    private fun setUserData(response: AuthResponse) {
        // 자동 로그인을 위한 유저 로그인 정보 저장
        ApplicationClass.sSharedPreferences.edit().putString(PROVIDER_ID, mAuth.providerId).apply()
        ApplicationClass.sSharedPreferences.edit().putString(USER_EMAIL, mAuth.email).apply()
        // 싱글톤 토큰 / 유저 정보 기입
        ApplicationClass.xAccessToken = "${response.tokenType} ${response.accessToken}"
        ApplicationClass.xRefreshToken = response.refreshToken
        Log.d("setUserData xAccessToken", ApplicationClass.xAccessToken)
        Log.d("setUserData xRefreshToken", ApplicationClass.xRefreshToken)

        UserCheckService(this).tryGetUser()
    }

    override fun onPostAuthSignInSuccess(response: AuthSignResponse) {
        Log.d("onPostAuthSignInSuccess", response.toString())
        setUserData(response.information)
        creator = false
    }

    override fun onPostAuthSignInFailed(message: String) {
        Log.d("onPostAuthSignInFailed", message)
        AuthSignService(this).tryPostAuthSignUp(mAuth)
    }

    override fun onPostAuthSignUpSuccess(response: AuthSignResponse) {
        Log.d("onPostAuthSignUpSuccess", response.toString())
        setUserData(response.information)
        // 비건 타입이 null 일 경우 테스트 연결
    }

    override fun onPostAuthSignUpFailed(message: String) {
        Log.d("onPostAuthSignUpFailed", message)
        dismissLoadingDialog()
    }


    override fun onGetUserSuccess(response: UserResponse) {
        Log.d("onGetUserSuccess", response.toString())
        ApplicationClass.xAuth = Auth(
            response.id,
            response.name,
            response.email,
            response.imageUrl,
            response.marketingConsent,
            response.veganType,
            response.provider,
            response.role,
            response.providerId
        )
        dismissLoadingDialog()
        moveToWelcomeOrTest()
    }

    override fun onGetUserFailure(message: String) {
        Log.d("onGetUserFailure", message)
        dismissLoadingDialog()
    }

}