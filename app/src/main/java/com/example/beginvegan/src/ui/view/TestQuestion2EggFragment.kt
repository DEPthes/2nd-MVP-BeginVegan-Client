package com.example.beginvegan.src.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestion1MilkBinding
import com.example.beginvegan.databinding.FragmentTestQuestion2EggBinding

class TestQuestion2EggFragment : BaseFragment<FragmentTestQuestion2EggBinding>(
    FragmentTestQuestion2EggBinding::bind,R.layout.fragment_test_question_2_egg) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (parentFragment as VeganTestOngoingFragment).setProgressValue(2)

        binding.btnAnswerA.setOnClickListener{
            (parentFragment as VeganTestOngoingFragment).goTestAfterFragment(
                getString(R.string.vegan_type_lacto_kr),
                getString(R.string.vegan_type_lacto_eng),
                getString(R.string.test_result_lacto)
            )
        }
        binding.btnAnswerB.setOnClickListener{
            val testQuestion3FishFragment = TestQuestion3FishFragment.newInstance()
            (parentFragment as VeganTestOngoingFragment).changeQuestion(testQuestion3FishFragment)
        }
    }

    companion object{
        fun newInstance(): TestQuestion2EggFragment{
            return TestQuestion2EggFragment()
        }
    }
}