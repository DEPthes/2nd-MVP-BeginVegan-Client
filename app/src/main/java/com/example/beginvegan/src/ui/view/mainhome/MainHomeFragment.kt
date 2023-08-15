package com.example.beginvegan.src.ui.view.mainhome

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainHomeBinding
import com.example.beginvegan.src.ui.view.mainhome.HomeMagazineAdapter

class MainHomeFragment : BaseFragment<FragmentMainHomeBinding>(
    FragmentMainHomeBinding::bind,R.layout.fragment_main_home ){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewpager = binding.vpMagazines
        val homeMagazineAdapter = HomeMagazineAdapter(this)
        viewpager.adapter = homeMagazineAdapter

        viewpager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                Log.d("TAG", "onViewCreated: ${position}")
                when(position){
                    0 -> { binding.ivMagazineIndicator0.setImageResource(R.drawable.shape_circle_home_indicator_active)
                        binding.ivMagazineIndicator1.setImageResource(R.drawable.shape_circle_home_indicator_inactive)
                    }
                    1 -> { binding.ivMagazineIndicator0.setImageResource(R.drawable.shape_circle_home_indicator_inactive)
                        binding.ivMagazineIndicator1.setImageResource(R.drawable.shape_circle_home_indicator_active)
                    }
                }
            }
        })
    }
}