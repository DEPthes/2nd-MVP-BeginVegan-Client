package com.example.beginvegan.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.beginvegan.R
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
        binding.tvVeganType.text = VeganTypes.valueOf(data.veganType).veganType
        binding.tvRecipeTitle.text = data.name

        val contents = data.blocks
        for(i:Int in 0 until contents.size){
            if(contents[i].sequence!=0){
                createNumTitle(contents[i].sequence)
            }
            Log.d("recipe", "onCreate: ${contents[i].content}")
            createTextView(contents[i].content)
        }

        //dialog 크기
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.apply {
            copyFrom(window!!.attributes)
            width = (context.resources.displayMetrics.widthPixels * 0.8).toInt()
            height = (context.resources.displayMetrics.heightPixels * 0.75).toInt()
            gravity = Gravity.CENTER
        }
        window!!.attributes = layoutParams
    }

    private fun createNumTitle(order:Int){
        val newTextView = TextView(context)
        newTextView.text = order.toString()
        newTextView.typeface = Typeface.DEFAULT_BOLD
        newTextView.textSize = context.resources.getDimension(R.dimen.text_20)/ context.resources.displayMetrics.scaledDensity
        newTextView.setTextColor(ContextCompat.getColor(context, R.color.color_primary3))
        newTextView.gravity = Gravity.CENTER
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        params.setMargins(0,80,0,0)
        params.gravity = Gravity.CENTER
        newTextView.layoutParams = params
        binding.llRecipeDetailContents.addView(newTextView)
    }
    private fun createTextView(text:String){
        val newTextView = TextView(context)
        newTextView.text = text
        newTextView.textSize = context.resources.getDimension(R.dimen.text_14)/ context.resources.displayMetrics.scaledDensity
        newTextView.setTextColor(ContextCompat.getColor(context, R.color.color_text_32))
        newTextView.gravity = Gravity.CENTER
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        params.setMargins(0,8,0,0)
        params.gravity = Gravity.CENTER
        newTextView.layoutParams = params
        Log.d("magazine", "createTextView: $text")
        binding.llRecipeDetailContents.addView(newTextView)
    }
}