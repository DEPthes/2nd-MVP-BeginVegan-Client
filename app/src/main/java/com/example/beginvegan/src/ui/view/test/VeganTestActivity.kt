package com.example.beginvegan.src.ui.view.test

import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivityVeganTestBinding
import com.example.beginvegan.src.ui.view.main.MainActivity

class VeganTestActivity : BaseActivity<ActivityVeganTestBinding>({ ActivityVeganTestBinding.inflate(it)}) {

    var userVeganType: String? = null
    var veganTypeEng: String? = null
    var typeDescription: String? = null
    var typeIndex = 0
    override fun init() {
        supportFragmentManager.beginTransaction().replace(R.id.fl_vegan_test,
            VeganTestBeforeFragment()
        ).commit()

    }
    // 유저 비건 타입 저장
    fun saveUserType(typeKr:String, typeEng:String, description:String, index:Int){
        if(userVeganType == null){
            userVeganType = typeKr
            veganTypeEng = typeEng
            typeDescription = description
            typeIndex = index
        }
    }
    //유저 비건 타입 초기화
    fun resetUserType(){
        userVeganType = null
        veganTypeEng = null
        typeDescription = null
        typeIndex = 0
    }

    // 유저 데이터 fragment에 전달
    fun getUserType():String?{ return userVeganType }
    fun getTypeEng():String?{ return veganTypeEng }
    fun getDescription():String?{ return typeDescription }
    fun getIndex():Int? { return typeIndex }

    //메인 홈으로 이동
    fun goHome(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    //테스트 진행 fragment (before, ongoing, after) 변경
    fun changeTestState(testState:Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_vegan_test,testState)
            addToBackStack(null)
            commit()
        }
    }
}