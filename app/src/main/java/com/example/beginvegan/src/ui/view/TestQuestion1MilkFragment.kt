package com.example.beginvegan.src.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestion1MilkBinding
import com.example.beginvegan.databinding.FragmentVeganTestAfterBinding

class TestQuestion1MilkFragment : BaseFragment<FragmentTestQuestion1MilkBinding>(
    FragmentTestQuestion1MilkBinding::bind,R.layout.fragment_test_question_1_milk) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAnswerA.setOnClickListener{
            (parentFragment as VeganTestOngoingFragment).goTestAfterFragment()
        }
        binding.btnAnswerB.setOnClickListener{
            val testQuestion2EggFragment = TestQuestion2EggFragment.newInstance()
            (parentFragment as VeganTestOngoingFragment).changeQuestion(testQuestion2EggFragment)
        }
    }

     companion object{
        fun newInstance(): TestQuestion1MilkFragment{
            return TestQuestion1MilkFragment()
        }
    }

}