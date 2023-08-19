package com.example.beginvegan.src.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivityMainBinding
import com.example.beginvegan.src.ui.view.mainhome.MainHomeFragment

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
                    //supportFragmentManager.beginTransaction().replace(R.id.fl_main,MainHomeFragment()).commit()
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

    fun goRecipe(position:Int){
        val fragment = MainRecipeFragment.newInstance()
        val bundle = Bundle()
        bundle.putInt("position", position)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.fl_main, fragment).commit()
        binding.bnvMain.selectedItemId = R.id.item_recipe
    }
}