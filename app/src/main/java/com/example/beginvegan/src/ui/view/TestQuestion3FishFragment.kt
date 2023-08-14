
package com.example.beginvegan.src.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestion2EggBinding
import com.example.beginvegan.databinding.FragmentTestQuestion3FishBinding

class TestQuestion3FishFragment : BaseFragment<FragmentTestQuestion3FishBinding>(
    FragmentTestQuestion3FishBinding::bind,R.layout.fragment_test_question_3_fish) {

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
            val testQuestion4MeatFragment = TestQuestion4MeatFragment.newInstance()
            (parentFragment as VeganTestOngoingFragment).changeQuestion(testQuestion4MeatFragment)
        }
    }

    companion object{
        fun newInstance(): TestQuestion3FishFragment{
            return TestQuestion3FishFragment()
        }
    }

}