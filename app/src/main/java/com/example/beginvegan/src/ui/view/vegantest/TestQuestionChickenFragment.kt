package com.example.beginvegan.src.ui.view.vegantest

import android.os.Bundle
import android.view.View
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestion5ChickenBinding

class TestQuestionChickenFragment : BaseFragment<FragmentTestQuestion5ChickenBinding>(
    FragmentTestQuestion5ChickenBinding::bind,R.layout.fragment_test_question_chicken) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (parentFragment as VeganTestOngoingFragment).setProgressValue(5)

        binding.btnAnswerA.setOnClickListener{
            (parentFragment as VeganTestOngoingFragment).goTestAfterFragment(
                getString(R.string.vegan_type_flexitarian_kr),
                getString(R.string.vegan_type_flexitarian_eng),
                getString(R.string.test_result_flexitarian)
            )
        }
        binding.btnAnswerB.setOnClickListener{
            (parentFragment as VeganTestOngoingFragment).goTestAfterFragment(
                getString(R.string.vegan_type_pollotarian_kr),
                getString(R.string.vegan_type_pollotarian_eng),
                getString(R.string.test_result_pollotarian)
            )
        }
    }
    companion object{
        fun newInstance(): TestQuestionChickenFragment {
            return TestQuestionChickenFragment()
        }
    }
}