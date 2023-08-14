package com.example.beginvegan.src.ui.view.vegantest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentVeganTestOngoingBinding

class VeganTestOngoingFragment : BaseFragment<FragmentVeganTestOngoingBinding>(
    FragmentVeganTestOngoingBinding::bind,R.layout.fragment_vegan_test_ongoing) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction().replace(
            R.id.fl_test_questions,
            TestQuestionMilkFragment.newInstance()
        ).commit()
    }

    companion object{
        fun newInstance(): VeganTestOngoingFragment {
            return VeganTestOngoingFragment()
        }
    }

    fun goTestAfterFragment(typeKr:String, typeEng:String, description:String){
        val veganTestAfterFragment =
            VeganTestAfterFragment.newInstance(typeKr, typeEng, description)
        (activity as VeganTestActivity).changeTestState(veganTestAfterFragment)
    }
    fun changeQuestion(nextQuestion:Fragment){
        childFragmentManager.beginTransaction().apply {
            replace(R.id.fl_test_questions, nextQuestion)
            addToBackStack(null)
            commit()
        }
    }
    fun setProgressValue(value:Int){
        binding.pbVeganTestOngoing.progress=value
    }
}