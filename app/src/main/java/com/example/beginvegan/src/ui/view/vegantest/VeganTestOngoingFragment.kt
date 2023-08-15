package com.example.beginvegan.src.ui.view.vegantest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentVeganTestOngoingBinding

class VeganTestOngoingFragment : BaseFragment<FragmentVeganTestOngoingBinding>(
    FragmentVeganTestOngoingBinding::bind,R.layout.fragment_vegan_test_ongoing) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val testQuestionMeatFragment = TestQuestionMeatFragment.newInstance()
        val testActivity = activity as VeganTestActivity
        changeQuestion(testQuestionMeatFragment)

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(childFragmentManager.backStackEntryCount>1){
                    if((childFragmentManager.backStackEntryCount-1) == testActivity.getIndex()){
                        testActivity.resetUserType()
                    }
                    childFragmentManager.popBackStackImmediate()
                }else{
                    requireActivity().supportFragmentManager.popBackStackImmediate()
                }
            }
        })
    }

    companion object{
        fun newInstance(): VeganTestOngoingFragment {
            return VeganTestOngoingFragment()
        }
    }

    // 질문 fragment 교체
    fun changeQuestion(nextQuestion:Fragment){
        childFragmentManager.beginTransaction().apply {
            replace(R.id.fl_test_questions, nextQuestion)
            addToBackStack(null)
            commit()
        }
    }
    // progress bar 제어
    fun setProgressValue(value:Int){
        binding.pbVeganTestOngoing.progress=value
    }
}