package com.example.beginvegan.src.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentVeganTestAfterBinding
import com.example.beginvegan.databinding.FragmentVeganTestBeforeBinding

class VeganTestBeforeFragment : BaseFragment<FragmentVeganTestBeforeBinding>(
    FragmentVeganTestBeforeBinding::bind,R.layout.fragment_vegan_test_before) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_vegan_test_before, container, false)
//    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun btnStart(){
        binding.btnVeganTestStart.setOnClickListener{
            val fragmentOngoing = VeganTestOngoingFragment.newInstance()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fl_vegan_test, fragmentOngoing)
                .addToBackStack(null)
                .commit()
        }
    }
}