package com.example.beginvegan.src.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivityVeganTestBinding

class VeganTestActivity : BaseActivity<ActivityVeganTestBinding>({ ActivityVeganTestBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vegan_test)
    }

    override fun init() {

    }
}