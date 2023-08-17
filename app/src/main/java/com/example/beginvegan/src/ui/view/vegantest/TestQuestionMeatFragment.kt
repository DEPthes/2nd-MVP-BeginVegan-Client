package com.example.beginvegan.src.ui.view.vegantest

import android.os.Bundle
import android.view.View
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestionMeatBinding

class TestQuestionMeatFragment : BaseFragment<FragmentTestQuestionMeatBinding>(
    FragmentTestQuestionMeatBinding::bind,R.layout.fragment_test_question_meat) {
    val INDEX = 1

    override fun init() {
        //progress bar
        (parentFragment as VeganTestOngoingFragment).setProgressValue(INDEX)

        val testActivity =(activity as VeganTestActivity)
        val testOngoingFragment = (parentFragment as VeganTestOngoingFragment)
        val testQuestionChickenFragment = TestQuestionChickenFragment.newInstance()

        binding.btnAnswerA.setOnClickListener{
            testActivity.saveUserType(
                getString(R.string.vegan_type_flexitarian_kr),
                getString(R.string.vegan_type_flexitarian_eng),
                getString(R.string.test_result_flexitarian),
                INDEX
            )
            testOngoingFragment.changeQuestion(testQuestionChickenFragment)
        }
        binding.btnAnswerB.setOnClickListener{
            testOngoingFragment.changeQuestion(testQuestionChickenFragment)
        }
    }

    companion object{
        fun newInstance(): TestQuestionMeatFragment {
            return TestQuestionMeatFragment()
        }
    }
}