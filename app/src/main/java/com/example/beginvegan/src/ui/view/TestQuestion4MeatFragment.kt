package com.example.beginvegan.src.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestion3FishBinding
import com.example.beginvegan.databinding.FragmentTestQuestion4MeatBinding

class TestQuestion4MeatFragment : BaseFragment<FragmentTestQuestion4MeatBinding>(
    FragmentTestQuestion4MeatBinding::bind,R.layout.fragment_test_question_4_meat) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (parentFragment as VeganTestOngoingFragment).setProgressValue(4)

        binding.btnAnswerA.setOnClickListener{
            (parentFragment as VeganTestOngoingFragment).goTestAfterFragment()
        }
        binding.btnAnswerB.setOnClickListener{
            val testQuestion5ChickenFragment = TestQuestion5ChickenFragment.newInstance()
            (parentFragment as VeganTestOngoingFragment).changeQuestion(testQuestion5ChickenFragment)
        }
    }
    companion object{
        fun newInstance(): TestQuestion4MeatFragment{
            return TestQuestion4MeatFragment()
        }
    }
}