package com.example.beginvegan.src.ui.view.vegantest

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivityVeganTestBinding
import com.example.beginvegan.src.ui.view.MainActivity

class VeganTestActivity : BaseActivity<ActivityVeganTestBinding>({ ActivityVeganTestBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun init() {
        supportFragmentManager.beginTransaction().replace(R.id.fl_vegan_test,
            VeganTestBeforeFragment()
        ).commit()

    }

    fun goHome(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun changeTestState(testState:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fl_vegan_test,testState).commit()
    }
    fun setTestResult(typeKr:String, typeEng:String, description:String){
//        val veganTestAfterFragment = supportFragmentManager.findFragmentById(R.id.fl_vegan_test) as VeganTestAfterFragment
//        veganTestAfterFragment.setTestResult(typeKr,typeEng,description)
    }
}