package com.example.beginvegan.src.ui.view

import android.app.AlertDialog
import android.text.Layout
import android.util.Log
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainProfileBinding
import com.example.beginvegan.src.ui.adapter.ProfileMyRecordsVPAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

class MainProfileFragment : BaseFragment<FragmentMainProfileBinding>(
    FragmentMainProfileBinding::bind, R.layout.fragment_main_profile
) {
    override fun init() {

        Log.d("TAG", "init: mainProfile")

        //ViewPager
        val vpMyRecords = binding.vpMyRecords
        vpMyRecords.adapter = ProfileMyRecordsVPAdapter(this)

        //tab
        Log.d("TAG", "init: mainProfile")
        //ViewPager
        val tabLayout = binding.tlRecords
        TabLayoutMediator(tabLayout,vpMyRecords){
                tab, pos ->
            when(pos){
                0-> tab.text = "나의 리뷰"
                1-> tab.text = "나의 스크랩"
            }
        }.attach()
//        requireActivity().supportFragmentManager.beginTransaction().add(R.id.vp_my_records, ProfileMyreviewFragment()).commit()
//        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                when(tab?.position){
//                    0 ->{replaceView(ProfileMyreviewFragment())}
//                    1 ->{replaceView(ProfileMyscrapFragment())}
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {}
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {}
//        })
//        tabLayout.addOnTabSelectedListener(object : TableLayout.OnTabSelectedListener{
//            override fun OnTabSelectedListener(tab: Layout.Tab?)
//
//        })
        //닉네임 수정 dialog

    }

    private fun replaceView(tab: Fragment){
        tab.let{
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.vp_my_records, it).commit()
        }
    }
}