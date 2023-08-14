package com.example.beginvegan.src.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentVeganTestAfterBinding
import com.example.beginvegan.databinding.FragmentVeganTestOngoingBinding

class VeganTestOngoingFragment : BaseFragment<FragmentVeganTestOngoingBinding>(
    FragmentVeganTestOngoingBinding::bind,R.layout.fragment_vegan_test_ongoing) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        childFragmentManager.beginTransaction().replace(
//            R.id.fl_test_questions,
//            TestQuestion1MilkFragment.newInstance()
//        ).commit()
        return inflater.inflate(R.layout.fragment_vegan_test_ongoing, container, false)
    }

    companion object{
        fun newInstance(): VeganTestOngoingFragment{
            return VeganTestOngoingFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}