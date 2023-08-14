package com.example.beginvegan.src.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentVeganTestAfterBinding

class VeganTestAfterFragment : BaseFragment<FragmentVeganTestAfterBinding>(
    FragmentVeganTestAfterBinding::bind,R.layout.fragment_vegan_test_after) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnVeganTestAgain.setOnClickListener{
            val veganTestBeforeFragment = VeganTestBeforeFragment.newInstance()
            (activity as VeganTestActivity).changeTestState(veganTestBeforeFragment)
        }
        binding.btnGoHome.setOnClickListener {
            (activity as VeganTestActivity).goHome()
        }
    }

    companion object{
        fun newInstance(): VeganTestAfterFragment{
            return VeganTestAfterFragment()
        }
    }
}