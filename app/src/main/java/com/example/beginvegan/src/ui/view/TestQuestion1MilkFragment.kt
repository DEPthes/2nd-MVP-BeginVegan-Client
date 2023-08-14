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

class TestQuestion1MilkFragment : BaseFragment<FragmentTestQuestion1MilkBinding>(
    FragmentTestQuestion1MilkBinding::bind,R.layout.fragment_test_question_1_milk) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_test_question_1_milk, container, false)
        return binding.root
    }

    companion object{
        fun newInstance(): TestQuestion1MilkFragment{
            return TestQuestion1MilkFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}