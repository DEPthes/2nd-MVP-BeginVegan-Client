package com.example.beginvegan.src.ui.view.vegantest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentVeganTestAfterBinding

class VeganTestAfterFragment : BaseFragment<FragmentVeganTestAfterBinding>(
    FragmentVeganTestAfterBinding::bind,R.layout.fragment_vegan_test_after) {

    override fun init() {
        val testActivity =(activity as VeganTestActivity)
        val veganTestBeforeFragment = VeganTestBeforeFragment.newInstance()

        //테스트 결과 받아와서 띄워주기
        binding.tvVeganTypeKr.setText(testActivity.getUserType())
        binding.tvVeganTypeEng.setText(testActivity.getTypeEng())
        binding.tvVeganTypeDescription.setText(testActivity.getDescription())

        binding.btnVeganTestAgain.setOnClickListener{
            testActivity.changeTestState(veganTestBeforeFragment)
            testActivity.resetUserType()
        }
        binding.btnGoHome.setOnClickListener {
            testActivity.goHome()
        }
    }

    companion object {
        fun newInstance(): VeganTestAfterFragment {
            return VeganTestAfterFragment()
        }
    }
}