package com.example.beginvegan.src.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivityLoginBinding
import com.example.beginvegan.databinding.ActivityRestaurantDetailBinding

class RestaurantDetailActivity : BaseActivity<ActivityRestaurantDetailBinding>({ ActivityRestaurantDetailBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)
    }
    override fun init() {

    }
}