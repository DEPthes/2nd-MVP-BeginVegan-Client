package com.example.beginvegan.src.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestion1MilkBinding
import com.example.beginvegan.databinding.FragmentVeganTestAfterBinding
import com.example.beginvegan.databinding.FragmentVeganTestOngoingBinding

class VeganTestOngoingFragment : BaseFragment<FragmentVeganTestOngoingBinding>(
    FragmentVeganTestOngoingBinding::bind,R.layout.fragment_vegan_test_ongoing) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction().replace(
            R.id.fl_test_questions,
            TestQuestion1MilkFragment.newInstance()
        ).commit()
    }

    companion object{
        fun newInstance(): VeganTestOngoingFragment{
            return VeganTestOngoingFragment()
        }
    }

    fun goTestAfterFragment(){
        val veganTestAfterFragment = VeganTestAfterFragment.newInstance()
        (activity as VeganTestActivity).changeTestState(veganTestAfterFragment)
    }
    fun changeQuestion(nextQuestion:Fragment){
        childFragmentManager.beginTransaction().apply {
            replace(R.id.fl_test_questions, nextQuestion)
            addToBackStack(null)
            commit()
        }
    }
}