package com.example.beginvegan.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.RadioGroup
import com.example.beginvegan.R
import com.example.beginvegan.databinding.DialogProfileEditVeganTypeBinding

class ProfileEditVeganTypeDialog(context: Context): Dialog(context) {
    private val binding: DialogProfileEditVeganTypeBinding = DialogProfileEditVeganTypeBinding.inflate(
        LayoutInflater.from(context))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //dialog 크기
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(window!!.attributes)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        window!!.attributes = layoutParams

        //setOnChangeListener
        binding.rgEditVeganType.setOnCheckedChangeListener { group, checkedId ->
            saveVeganType(checkedId)
        }
        //Cancel
        binding.btnCancel.setOnClickListener {
            this.dismiss()
        }
    }

    private fun saveVeganType(checkedId:Int){
        //저장
        this.dismiss()
    }
}