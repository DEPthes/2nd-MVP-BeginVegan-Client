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
        return inflater.inflate(R.layout.fragment_vegan_test_ongoing, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}