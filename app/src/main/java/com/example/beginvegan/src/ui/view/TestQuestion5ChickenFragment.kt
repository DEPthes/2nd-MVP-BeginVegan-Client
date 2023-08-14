package com.example.beginvegan.src.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestion4MeatBinding
import com.example.beginvegan.databinding.FragmentTestQuestion5ChickenBinding

class TestQuestion5ChickenFragment : BaseFragment<FragmentTestQuestion5ChickenBinding>(
    FragmentTestQuestion5ChickenBinding::bind,R.layout.fragment_test_question_5_chicken) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAnswerA.setOnClickListener{
            (parentFragment as VeganTestOngoingFragment).goTestAfterFragment()
        }
        binding.btnAnswerB.setOnClickListener{
            (parentFragment as VeganTestOngoingFragment).goTestAfterFragment()
        }
    }
    companion object{
        fun newInstance(): TestQuestion5ChickenFragment{
            return TestQuestion5ChickenFragment()
        }
    }
}