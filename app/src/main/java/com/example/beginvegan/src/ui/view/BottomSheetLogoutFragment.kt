package com.example.beginvegan.src.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.databinding.DialogBottomSheetLogoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetLogoutFragment : BottomSheetDialogFragment() {
    private val binding: DialogBottomSheetLogoutBinding by lazy{
        DialogBottomSheetLogoutBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = binding.root
        binding.btnLogout.setOnClickListener {
            onButtonClick()
        }
        return view
    }

    // 버튼 클릭 이벤트 처리
    fun onButtonClick() {
        // 버튼이 클릭되었을 때 실행되는 코드
        Log.d("TAG", "onButtonClick: ")
        this.dismiss() // BottomSheet 닫기
    }
}