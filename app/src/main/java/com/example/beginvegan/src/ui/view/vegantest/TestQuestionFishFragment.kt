
package com.example.beginvegan.src.ui.view.vegantest

import android.os.Bundle
import android.view.View
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestionFishBinding

class TestQuestionFishFragment : BaseFragment<FragmentTestQuestionFishBinding>(
    FragmentTestQuestionFishBinding::bind,R.layout.fragment_test_question_fish) {
    val INDEX = 3

    override fun init() {
        //progress bar
        (parentFragment as VeganTestOngoingFragment).setProgressValue(INDEX)

        val testActivity =(activity as VeganTestActivity)
        val testOngoingFragment = (parentFragment as VeganTestOngoingFragment)
        val testQuestionEggFragment = TestQuestionEggFragment.newInstance()

        binding.btnAnswerA.setOnClickListener{
            testActivity.saveUserType(
                getString(R.string.vegan_type_pescatarian_kr),
                getString(R.string.vegan_type_pescatarian_eng),
                getString(R.string.test_result_pescatarian),
                INDEX
            )
            testOngoingFragment.changeQuestion(testQuestionEggFragment)
        }
        binding.btnAnswerB.setOnClickListener{
            testOngoingFragment.changeQuestion(testQuestionEggFragment)
        }
    }

    companion object{
        fun newInstance(): TestQuestionFishFragment {
            return TestQuestionFishFragment()
        }
    }

}