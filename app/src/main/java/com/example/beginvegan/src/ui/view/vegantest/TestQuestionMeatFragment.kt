package com.example.beginvegan.src.ui.view.vegantest

import android.os.Bundle
import android.view.View
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestion4MeatBinding

class TestQuestionMeatFragment : BaseFragment<FragmentTestQuestion4MeatBinding>(
    FragmentTestQuestion4MeatBinding::bind,R.layout.fragment_test_question_meat) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (parentFragment as VeganTestOngoingFragment).setProgressValue(4)

        binding.btnAnswerA.setOnClickListener{
            (parentFragment as VeganTestOngoingFragment).goTestAfterFragment(
                getString(R.string.vegan_type_pescatarian_kr),
                getString(R.string.vegan_type_pescatarian_eng),
                getString(R.string.test_result_pescatarian)
            )
        }
        binding.btnAnswerB.setOnClickListener{
            val testQuestion5ChickenFragment = TestQuestionChickenFragment.newInstance()
            (parentFragment as VeganTestOngoingFragment).changeQuestion(testQuestion5ChickenFragment)
        }
    }
    companion object{
        fun newInstance(): TestQuestionMeatFragment {
            return TestQuestionMeatFragment()
        }
    }
}