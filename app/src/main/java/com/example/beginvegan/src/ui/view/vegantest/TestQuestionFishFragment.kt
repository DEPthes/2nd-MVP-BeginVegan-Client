
package com.example.beginvegan.src.ui.view.vegantest

import android.os.Bundle
import android.view.View
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestion3FishBinding

class TestQuestionFishFragment : BaseFragment<FragmentTestQuestion3FishBinding>(
    FragmentTestQuestion3FishBinding::bind,R.layout.fragment_test_question_fish) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (parentFragment as VeganTestOngoingFragment).setProgressValue(3)

        binding.btnAnswerA.setOnClickListener{
            (parentFragment as VeganTestOngoingFragment).goTestAfterFragment(
                getString(R.string.vegan_type_lacto_ovo_kr),
                getString(R.string.vegan_type_lacto_ovo_eng),
                getString(R.string.test_result_lacto_ovo)
            )
        }
        binding.btnAnswerB.setOnClickListener{
            val testQuestion4MeatFragment = TestQuestionMeatFragment.newInstance()
            (parentFragment as VeganTestOngoingFragment).changeQuestion(testQuestion4MeatFragment)
        }
    }

    companion object{
        fun newInstance(): TestQuestionFishFragment {
            return TestQuestionFishFragment()
        }
    }

}