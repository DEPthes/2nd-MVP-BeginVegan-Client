package com.example.beginvegan.src.ui.view

import androidx.fragment.app.Fragment
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivityMainBinding
import com.example.beginvegan.src.ui.view.mainhome.MainHomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it)}) {
    override fun init() {
        supportFragmentManager.beginTransaction().replace(R.id.fl_main, MainHomeFragment()).commit()

        binding.bnvMain.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item_home->{
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,
                        MainHomeFragment()
                    ).commit()
                }
                R.id.item_map->{
                    //supportFragmentManager.beginTransaction().replace(R.id.fl_main,MainHomeFragment()).commit()
                }
                R.id.item_recipe->{
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,MainRecipeFragment()).commit()
                }
                R.id.item_profile->{
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,MainProfileFragment()).commit()
                }
            }
            true
        }
    }

    fun goRecipe(){
        supportFragmentManager.beginTransaction().replace(R.id.fl_main,MainRecipeFragment()).commit()
        binding.bnvMain.selectedItemId = R.id.item_recipe
    }

//    fun goMap(){
//        //지도로 이동
//    }
}