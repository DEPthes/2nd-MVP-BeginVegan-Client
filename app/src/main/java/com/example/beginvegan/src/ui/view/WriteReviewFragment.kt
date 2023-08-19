package com.example.beginvegan.src.ui.view

import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentWriteReviewBinding

class WriteReviewFragment : BaseFragment<FragmentWriteReviewBinding>(
    FragmentWriteReviewBinding::bind,
    R.layout.fragment_write_review
) {
    override fun init() {
        // callback Fragment Review
        binding.bSaveReview.setOnClickListener {
                val result = binding.etWriteReview.text
                parentFragmentManager.setFragmentResult("review", bundleOf("bundleKey" to result))
                parentFragmentManager.popBackStack()
            }
        }
}