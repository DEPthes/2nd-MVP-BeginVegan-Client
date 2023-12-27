package com.example.beginvegan.util

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import com.example.beginvegan.databinding.DialogLogoutBinding
import com.example.beginvegan.src.data.model.auth.AuthSignOutInterface
import com.example.beginvegan.src.data.model.auth.AuthSignOutResponse
import com.example.beginvegan.src.data.model.auth.AuthSignOutService
import com.example.beginvegan.src.ui.view.login.LoginActivity

class LogoutDialog(context: Context): Dialog(context), AuthSignOutInterface{
    private val binding: DialogLogoutBinding = DialogLogoutBinding.inflate(
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

        binding.btnLogout.setOnClickListener {
            //로그아웃
            AuthSignOutService(this).tryPostAuthSignOut()
        }
        binding.btnBack.setOnClickListener {
            //돌아가기
            this.dismiss()
        }
    }

    override fun onPostAuthSignOutSuccess(response: AuthSignOutResponse) {
        this.dismiss()
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

    override fun onPostAuthSignOutFailure(message: String) {
        this.dismiss()
        Toast.makeText(context,"로그아웃 실패",Toast.LENGTH_SHORT).show()
    }
}