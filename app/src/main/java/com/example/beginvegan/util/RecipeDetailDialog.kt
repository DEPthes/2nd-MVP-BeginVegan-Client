package com.example.beginvegan.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import com.example.beginvegan.databinding.DialogRecipeDetailBinding
import com.example.beginvegan.src.data.model.recipe.RecipeDetail

class RecipeDetailDialog(context: Context,private val data: RecipeDetail): Dialog(context) {

    private val binding: DialogRecipeDetailBinding = DialogRecipeDetailBinding.inflate(
        LayoutInflater.from(context))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //데이터 연결
        binding.tvVeganType.text = data.veganType
        binding.tvRecipeTitle.text = data.name
        var ingredients = "&lt;재료&gt;\n"
        for(i:Int in 0..data.ingredients.size){
            if(i!=0){ingredients+=", "}
            ingredients += data.ingredients[i].name
        }
//        binding.tvRecipeIngredients.text =

        //dialog 크기
//        val layoutParams = WindowManager.LayoutParams()
//        val dialogView = findViewById<View>(R.id.fl_magazine_dialog)
//        layoutParams.copyFrom(window!!.attributes)
//        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
//        window!!.attributes = layoutParams
    }
}