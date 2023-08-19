package com.example.beginvegan.src.ui.view

import android.content.Intent
import android.util.Log
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainProfileBinding
import com.example.beginvegan.src.ui.adapter.ProfileMyRecordsVPAdapter
import com.example.beginvegan.src.ui.view.vegantest.VeganTestActivity
import com.example.beginvegan.util.LogoutDialog
import com.example.beginvegan.util.ProfileEditNameDialog
import com.example.beginvegan.util.ProfileEditVeganTypeDialog
import com.google.android.material.tabs.TabLayoutMediator

class MainProfileFragment : BaseFragment<FragmentMainProfileBinding>(
    FragmentMainProfileBinding::bind, R.layout.fragment_main_profile
), BottomSheetLogoutFragment.MyFragmentInteractionListener {
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
        //테스트 바로가기
        binding.bGoVeganTest.setOnClickListener {
            val intent = Intent(this.context, VeganTestActivity::class.java)
            startActivity(intent)
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
        val bottomSheet =BottomSheetLogoutFragment()
        bottomSheet.listener = this
        bottomSheet.show(requireActivity().supportFragmentManager, null)
    }
    //로그아웃 dialog
    fun openLogoutDialog() {
        Log.d("TAG", "openLogoutDialog: 로그아웃 dialog open")
        val logoutDialog = LogoutDialog(requireContext())
        logoutDialog.show()
    }

    override fun onButtonClicked() {
        Log.d("TAG", "onButtonClicked: ")
        openLogoutDialog()
    }

}