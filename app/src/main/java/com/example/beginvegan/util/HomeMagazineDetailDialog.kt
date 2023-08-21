package com.example.beginvegan.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.beginvegan.R
import com.example.beginvegan.databinding.DialogHomeMagazineDetailBinding
import com.example.beginvegan.src.data.model.magazine.MagazineDetail
import com.example.beginvegan.src.ui.view.MainActivity

class HomeMagazineDetailDialog(context: Context, private val data:MagazineDetail): Dialog(context) {

    private val binding: DialogHomeMagazineDetailBinding = DialogHomeMagazineDetailBinding.inflate(
        LayoutInflater.from(context))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //데이터 연결
        binding.tvMagazineTitle.text = data.title
        val contents = data.magazineContents
        for(i:Int in 0 until contents.size){
            Log.d("magazine", "onCreate: ${contents[i].content}")
            if(i==0){
                Log.d("magazine", "onCreate: create image view")
                createImageView(contents[i].content)
            }else{
                createTextView(contents[i].content)
            }
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
    private fun createTextView(text:String){
        val newTextView = TextView(context)
        newTextView.text = text
        newTextView.textSize = context.resources.getDimension(R.dimen.text_14)/ context.resources.displayMetrics.scaledDensity
        newTextView.setTextColor(ContextCompat.getColor(context,R.color.color_text_32))
        newTextView.gravity = Gravity.CENTER
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        params.setMargins(0,100,0,0)
        params.gravity = Gravity.CENTER
        newTextView.layoutParams = params
        Log.d("magazine", "createTextView: $text")
        binding.llMagazineContents.addView(newTextView)
    }

    private fun createImageView(url:String){
        val newImgView = ImageView(context)
        Glide.with(context).load(url).into(newImgView)
        newImgView.scaleType = ImageView.ScaleType.FIT_START
        binding.llMagazineContents.addView(newImgView)
    }
}