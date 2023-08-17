package com.example.beginvegan.src.ui.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import com.example.beginvegan.databinding.DialogRecipeDetailBinding

class RecipeDetailDialog(context: Context): Dialog(context) {

    private val binding: DialogRecipeDetailBinding = DialogRecipeDetailBinding.inflate(
        LayoutInflater.from(context))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //데이터 연결
//        binding.tvVeganType.setText("")
//        binding.tvRecipeTitle.setText("")
//        binding.tvRecipeContent.setText("")

        //dialog 크기
//        val layoutParams = WindowManager.LayoutParams()
//        val dialogView = findViewById<View>(R.id.fl_magazine_dialog)
//        layoutParams.copyFrom(window!!.attributes)
//        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
//        window!!.attributes = layoutParams
    }
}