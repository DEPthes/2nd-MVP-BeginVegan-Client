package com.example.beginvegan.src.ui.view.vegantest

import android.os.Bundle
import android.view.View
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestion1MilkBinding

class TestQuestionMilkFragment : BaseFragment<FragmentTestQuestion1MilkBinding>(
    FragmentTestQuestion1MilkBinding::bind,R.layout.fragment_test_question_milk) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (parentFragment as VeganTestOngoingFragment).setProgressValue(1)

        binding.btnAnswerA.setOnClickListener{
            (parentFragment as VeganTestOngoingFragment).goTestAfterFragment(
                getString(R.string.vegan_type_vegan_kr),
                getString(R.string.vegan_type_vegan_eng),
                getString(R.string.test_result_vegan))
        }
        binding.btnAnswerB.setOnClickListener{
            val testQuestion2EggFragment = TestQuestionEggFragment.newInstance()
            (parentFragment as VeganTestOngoingFragment).changeQuestion(testQuestion2EggFragment)
        }
    }

     companion object{
        fun newInstance(): TestQuestionMilkFragment {
            return TestQuestionMilkFragment()
        }
    }

}