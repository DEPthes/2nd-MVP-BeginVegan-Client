package com.example.beginvegan.src.ui.view

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.databinding.DialogBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class VeganMapBottomSheetDialog() : BottomSheetDialogFragment() {
    private var _binding: DialogBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
        super.onCreate(savedInstanceState)
        val dialog = BottomSheetDialog(requireContext(),theme).apply{
            behavior.isDraggable
            behavior.peekHeight = resources.getDimension(R.dimen.margin_76).toInt()
        }
//        mBinding = DialogBottomSheetBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        window!!.setBackgroundDrawable(ColorDrawable())
//        window!!.setDimAmount(0F)
    }

}