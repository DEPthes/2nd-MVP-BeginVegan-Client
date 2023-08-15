package com.example.beginvegan.src.ui.view.vegantest

import android.os.Bundle
import android.view.View
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestionChickenBinding

class TestQuestionChickenFragment : BaseFragment<FragmentTestQuestionChickenBinding>(
    FragmentTestQuestionChickenBinding::bind,R.layout.fragment_test_question_chicken) {
    val INDEX = 2
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //progress bar
        (parentFragment as VeganTestOngoingFragment).setProgressValue(INDEX)

        val testActivity =(activity as VeganTestActivity)
        val testOngoingFragment = (parentFragment as VeganTestOngoingFragment)
        val testQuestionFishFragment = TestQuestionFishFragment.newInstance()

        binding.btnAnswerA.setOnClickListener{
            testActivity.saveUserType(
                getString(R.string.vegan_type_pollotarian_kr),
                getString(R.string.vegan_type_pollotarian_eng),
                getString(R.string.test_result_pollotarian),
                INDEX
            )
            testOngoingFragment.changeQuestion(testQuestionFishFragment)
        }
        binding.btnAnswerB.setOnClickListener{
            testOngoingFragment.changeQuestion(testQuestionFishFragment)
        }
    }
    companion object{
        fun newInstance(): TestQuestionChickenFragment {
            return TestQuestionChickenFragment()
        }
    }
}