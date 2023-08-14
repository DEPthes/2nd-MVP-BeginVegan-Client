package com.example.beginvegan.src.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentTestQuestion3FishBinding
import com.example.beginvegan.databinding.FragmentTestQuestion4MeatBinding

class TestQuestion4MeatFragment : BaseFragment<FragmentTestQuestion4MeatBinding>(
    FragmentTestQuestion4MeatBinding::bind,R.layout.fragment_test_question_4_meat) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_test_question_4_meat, container, false)
        return binding.root
    }
    companion object{
        fun newInstance(): TestQuestion4MeatFragment{
            return TestQuestion4MeatFragment()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}