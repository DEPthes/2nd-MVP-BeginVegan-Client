package com.example.beginvegan.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.beginvegan.databinding.DialogProfileEditNameBinding

class ProfileEditNameDialog(context: Context, private val originalName:String): Dialog(context) {
    private val binding: DialogProfileEditNameBinding = DialogProfileEditNameBinding.inflate(
        LayoutInflater.from(context))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.tietNewName.hint = originalName
        binding.btnConfirm.setOnClickListener {
            //name 수정 처리
            if(!binding.tietNewName.text.isNullOrEmpty()){
                listener?.editNameOnSaveClicked(binding.tietNewName.text.toString())
            }
            //서버에 저장
            this.dismiss()
        }
    }

    //인터페이스
    interface EditNameDialogListener {
        fun editNameOnSaveClicked(name: String)
    }
    private var listener: EditNameDialogListener? = null

    fun setListener(listener: EditNameDialogListener) {
        this.listener = listener
    }
}