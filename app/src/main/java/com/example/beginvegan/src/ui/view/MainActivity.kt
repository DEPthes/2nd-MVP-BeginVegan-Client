package com.example.beginvegan.src.ui.view

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.beginvegan.R
import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivityMainBinding
import com.example.beginvegan.src.ui.view.mainhome.MainHomeFragment
import com.google.android.gms.location.LocationServices

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun init() {
        supportFragmentManager.beginTransaction().replace(R.id.fl_main,MainHomeFragment()).commit()

        binding.bnvMain.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item_home->{
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,MainHomeFragment()).commit()
                }
                R.id.item_map->{
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,VeganMapFragment()).setReorderingAllowed(true).commitAllowingStateLoss()
                }
                R.id.item_recipe->{
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,MainRecipeFragment()).commit()
                }
                R.id.item_profile->{
                    Log.d("TAG", "init: main activity")
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,MainProfileFragment()).commit()
                }
            }
            true
        }
    }

    //홈에서 추천 레시피 클릭했을 때
    fun goRecipe(id:Int){
        val fragment = MainRecipeFragment()
        val bundle = Bundle()
        bundle.putInt("recipeId",id)
        Log.d("TAG", "goRecipe: in MainActivity $id")
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.fl_main, fragment).commit()
        binding.bnvMain.selectedItemId = R.id.item_recipe
    }
    //매거진, 레시피 상세페이지 TextView 추가
//    fun createTextView(text:String, linear:LinearLayout){
//        val newTextView = TextView(this)
//        newTextView.text = text
//        newTextView.textSize = R.dimen.text_14.toFloat()
//        newTextView.setTextColor(ContextCompat.getColor(this,R.color.color_text_32))
//        newTextView.gravity = Gravity.CENTER
//        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//        newTextView.layoutParams = params
//        linear.addView(newTextView)
//    }


}