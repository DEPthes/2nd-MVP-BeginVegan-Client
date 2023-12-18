package com.example.beginvegan.src.ui.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.databinding.DialogBottomSheetLogoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetLogoutFragment: BottomSheetDialogFragment() {
    var listener: MyFragmentInteractionListener? = null

    private val binding: DialogBottomSheetLogoutBinding by lazy {
        DialogBottomSheetLogoutBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = binding.root

        binding.btnLogout.setOnClickListener {
            callOtherFragmentFunction()
            this.dismiss()
        }

        return view
    }

    interface MyFragmentInteractionListener {
        fun onButtonClicked()
    }
    fun callOtherFragmentFunction() {
        listener?.onButtonClicked()
    }
}