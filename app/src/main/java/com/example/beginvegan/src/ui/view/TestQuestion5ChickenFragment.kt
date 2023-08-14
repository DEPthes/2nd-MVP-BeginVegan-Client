package com.example.beginvegan.src.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestion4MeatBinding
import com.example.beginvegan.databinding.FragmentTestQuestion5ChickenBinding

class TestQuestion5ChickenFragment : BaseFragment<FragmentTestQuestion5ChickenBinding>(
    FragmentTestQuestion5ChickenBinding::bind,R.layout.fragment_test_question_5_chicken) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_test_question_5_chicken, container, false)
        return binding.root
    }
    companion object{
        fun newInstance(): TestQuestion5ChickenFragment{
            return TestQuestion5ChickenFragment()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}