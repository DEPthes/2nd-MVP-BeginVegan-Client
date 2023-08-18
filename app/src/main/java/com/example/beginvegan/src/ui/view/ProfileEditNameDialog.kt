package com.example.beginvegan.src.ui.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import com.example.beginvegan.databinding.DialogProfileEditNameBinding

class ProfileEditNameDialog(context: Context): Dialog(context) {
    private val binding: DialogProfileEditNameBinding = DialogProfileEditNameBinding.inflate(
        LayoutInflater.from(context))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnConfirm.setOnClickListener {
            this.dismiss()
            //name 수정 처리
        }
    }
}