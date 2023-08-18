package com.example.beginvegan.src.ui.view

import android.util.Log
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainProfileBinding
import com.example.beginvegan.src.ui.adapter.ProfileMyRecordsVPAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainProfileFragment : BaseFragment<FragmentMainProfileBinding>(
    FragmentMainProfileBinding::bind, R.layout.fragment_main_profile
) {
    override fun init() {

        Log.d("TAG", "init: mainProfile")
        //ViewPager
        val vpMyRecords = binding.vpMyRecords
        val tabLayout = binding.tlRecords
        val profileMyRecordsVPAdapter = ProfileMyRecordsVPAdapter(this)
        vpMyRecords.adapter = profileMyRecordsVPAdapter
        TabLayoutMediator(tabLayout,vpMyRecords){
            tab, pos ->
            when(pos){
                0-> tab.text = "나의 리뷰"
                1-> tab.text = "나의 스크랩"
            }
        }.attach()


    }

}