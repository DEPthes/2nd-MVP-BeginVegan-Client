package com.example.beginvegan.src.ui.view.vegantest

import android.os.Bundle
import android.view.View
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestion2EggBinding

class TestQuestionEggFragment : BaseFragment<FragmentTestQuestion2EggBinding>(
    FragmentTestQuestion2EggBinding::bind,R.layout.fragment_test_question_egg) {
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
            val testQuestion3FishFragment = TestQuestionFishFragment.newInstance()
            (parentFragment as VeganTestOngoingFragment).changeQuestion(testQuestion3FishFragment)
        }
    }

    companion object{
        fun newInstance(): TestQuestionEggFragment {
            return TestQuestionEggFragment()
        }
    }
}