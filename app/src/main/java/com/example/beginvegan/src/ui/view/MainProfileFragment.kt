package com.example.beginvegan.src.ui.view

import android.app.AlertDialog
import android.util.Log
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainProfileBinding
import com.example.beginvegan.src.ui.adapter.ProfileMyRecordsVPAdapter

class MainProfileFragment : BaseFragment<FragmentMainProfileBinding>(
    FragmentMainProfileBinding::bind, R.layout.fragment_main_profile
) {
    override fun init() {

        Log.d("TAG", "init: mainProfile")


        //ViewPager
        val vpMyRecords = binding.vpMyRecords
        val tabLayout = binding.tlRecords
//        val profileMyRecordsVPAdapter = ProfileMyRecordsVPAdapter(this)
        vpMyRecords.adapter = ProfileMyRecordsVPAdapter(this)

        //닉네임 수정 dialog

    }

}