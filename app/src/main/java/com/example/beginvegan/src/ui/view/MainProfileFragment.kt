package com.example.beginvegan.src.ui.view

import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainProfileBinding
import com.example.beginvegan.src.ui.adapter.ProfileMyRecordsVPAdapter
import com.example.beginvegan.util.ProfileEditNameDialog
import com.example.beginvegan.util.ProfileEditVeganTypeDialog
import com.google.android.material.tabs.TabLayoutMediator

class MainProfileFragment : BaseFragment<FragmentMainProfileBinding>(
    FragmentMainProfileBinding::bind, R.layout.fragment_main_profile
) {
    override fun init() {
        //ViewPager
        val vpMyRecords = binding.vpMyRecords
        vpMyRecords.adapter = ProfileMyRecordsVPAdapter(this)
        //tab
        val tabLayout = binding.tlRecords
        TabLayoutMediator(tabLayout,vpMyRecords){
                tab, pos ->
            when(pos){
                0-> tab.text = "나의 리뷰"
                1-> tab.text = "나의 스크랩"
            }
        }.attach()

        //닉네임 수정 dialog
        binding.ibEditUsername.setOnClickListener{
            openEditUserNameDialog()
        }
        //Vegan Type 수정 dialog
        binding.ibEditVeganType.setOnClickListener{
            openEditVeganTypeDialog()
        }
        //로그아웃 more button 클릭
        binding.btnProfileMore.setOnClickListener {
            openBottomSheetLogout()
        }
    }

    //닉네임 수정 dialog
    private fun openEditUserNameDialog(){
        val editNameDialog = ProfileEditNameDialog(requireContext())
        editNameDialog.show()
    }
    //Vegan Type 수정 dialog
    private fun openEditVeganTypeDialog(){
        val editVeganTypeDialog = ProfileEditVeganTypeDialog(requireContext())
        editVeganTypeDialog.show()
    }
    //로그아웃 more button
    private fun openBottomSheetLogout(){
        BottomSheetLogoutFragment().show(requireActivity().supportFragmentManager, null)
    }
}