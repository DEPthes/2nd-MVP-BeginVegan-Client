package com.example.beginvegan.src.ui.view.main

import android.os.Bundle
import android.util.Log
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivityMainBinding
import com.example.beginvegan.src.ui.view.recipe.MainRecipeFragment
import com.example.beginvegan.src.ui.view.home.MainHomeFragment
import com.example.beginvegan.src.ui.view.map.VeganMapFragment
import com.example.beginvegan.src.ui.view.profile.MainProfileFragment

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
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,
                        VeganMapFragment()
                    ).setReorderingAllowed(true).commitAllowingStateLoss()
                }
                R.id.item_recipe->{
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,
                        MainRecipeFragment()
                    ).commit()
                }
                R.id.item_profile->{
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,
                        MainProfileFragment()
                    ).commit()
                }
            }
            true
        }
    }

    fun selectNavigationRecipe(){
        binding.bnvMain.selectedItemId = R.id.item_recipe
    }
    fun selectNavigationRestaurant(){
        binding.bnvMain.selectedItemId = R.id.item_map
    }
    //홈에서 추천 레시피 클릭했을 때
//    fun goRecipe(id:Int){
//        val fragment = MainRecipeFragment()
//        val bundle = Bundle()
//        bundle.putInt("recipeId",id)
//        Log.d("TAG", "goRecipe: in MainActivity $id")
//        fragment.arguments = bundle
//
//        supportFragmentManager.beginTransaction().replace(R.id.fl_main, fragment).commit()
//        binding.bnvMain.selectedItemId = R.id.item_recipe
//    }


}