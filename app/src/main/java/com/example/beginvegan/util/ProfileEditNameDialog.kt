package com.example.beginvegan.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.beginvegan.databinding.DialogProfileEditNameBinding
import com.example.beginvegan.src.data.model.user.UserModifyNameInterface
import com.example.beginvegan.src.data.model.user.UserModifyNameResponse
import com.example.beginvegan.src.data.model.user.UserModifyNameService

class ProfileEditNameDialog(context: Context, private val originalName:String): Dialog(context),
    UserModifyNameInterface{
    private val binding: DialogProfileEditNameBinding = DialogProfileEditNameBinding.inflate(
        LayoutInflater.from(context))

    val TAG = "EditName"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        binding.tietNewName.hint = originalName
        binding.btnConfirm.setOnClickListener {
            val newName = binding.tietNewName.text.toString()
            //name 수정 처리
            if(!binding.tietNewName.text.isNullOrEmpty()){
                listener?.editNameOnSaveClicked(newName)
            }
            //서버에 저장
            UserModifyNameService(this).tryPostUserModifyName(newName)
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

    //서버 - 유저
    override fun onPostUserModifyNameSuccess(response: UserModifyNameResponse) {
        Log.d(TAG, "onPostUserModifyNameSuccess: ")
    }
    override fun onPostUserModifyNameFailure(message: String) {
        Log.d(TAG, "onPostUserModifyNameFailure: $message")
    }
}