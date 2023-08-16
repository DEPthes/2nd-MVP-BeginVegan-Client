package com.example.beginvegan.src.ui.view

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.core.view.isInvisible
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseActivity
import com.example.beginvegan.databinding.ActivityWelcomeBinding

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>({ActivityWelcomeBinding.inflate(it)}) {
    override fun init() {
        fadeIn(binding.ivWelcomeBeginvegan)
        Handler(mainLooper).postDelayed({
            moveToMain()
        },3000)
    }
    private fun moveToMain(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun fadeIn(imageView: ImageView) {
        imageView.isInvisible = false
        val fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f)
        fadeIn.duration = 2000
        fadeIn.start()
    }
}