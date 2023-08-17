package com.example.beginvegan.src.ui.view.vegantest

import android.os.Bundle
import android.view.View
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentVeganTestBeforeBinding

class VeganTestBeforeFragment : BaseFragment<FragmentVeganTestBeforeBinding>(
    FragmentVeganTestBeforeBinding::bind,R.layout.fragment_vegan_test_before) {

    override fun init() {
        binding.btnVeganTestStart.setOnClickListener{
            val fragmentOngoing = VeganTestOngoingFragment.newInstance()
            (activity as VeganTestActivity).changeTestState(fragmentOngoing)
        }
        binding.btnVeganTestNext.setOnClickListener{
            (activity as VeganTestActivity).goHome()
        }
    }

    companion object{
        fun newInstance(): VeganTestBeforeFragment {
            return VeganTestBeforeFragment()
        }
    }

}