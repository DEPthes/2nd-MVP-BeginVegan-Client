package com.example.beginvegan.src.ui.view.vegantest

import android.os.Bundle
import android.view.View
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestionEggBinding

class TestQuestionEggFragment : BaseFragment<FragmentTestQuestionEggBinding>(
    FragmentTestQuestionEggBinding::bind,R.layout.fragment_test_question_egg) {
    val INDEX = 4

    override fun init() {
        //progress bar
        (parentFragment as VeganTestOngoingFragment).setProgressValue(INDEX)

        val testActivity =(activity as VeganTestActivity)
        val testOngoingFragment = (parentFragment as VeganTestOngoingFragment)
        val testQuestionMilkFragment = TestQuestionMilkFragment.newInstance()

        binding.btnAnswerA.setOnClickListener{
            testActivity.saveUserType(
                getString(R.string.vegan_type_lacto_ovo_kr),
                getString(R.string.vegan_type_lacto_ovo_eng),
                getString(R.string.test_result_lacto_ovo),
                INDEX
            )
            testOngoingFragment.changeQuestion(testQuestionMilkFragment)
        }
        binding.btnAnswerB.setOnClickListener{
            testOngoingFragment.changeQuestion(testQuestionMilkFragment)
        }
    }

    companion object{
        fun newInstance(): TestQuestionEggFragment {
            return TestQuestionEggFragment()
        }
    }
}