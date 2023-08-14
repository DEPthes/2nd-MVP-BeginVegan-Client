
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_test_question_3_fish, container, false)
        return binding.root
    }

    companion object{
        fun newInstance(): TestQuestion3FishFragment{
            return TestQuestion3FishFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}