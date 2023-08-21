package com.example.beginvegan.src.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.beginvegan.R
import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainProfileBinding
import com.example.beginvegan.src.data.model.user.UserInterface
import com.example.beginvegan.src.data.model.user.UserVeganService
import com.example.beginvegan.src.ui.adapter.ProfileMyRecordsVPAdapter
import com.example.beginvegan.src.ui.view.vegantest.VeganTestActivity
import com.example.beginvegan.util.LogoutDialog
import com.example.beginvegan.util.ProfileEditNameDialog
import com.example.beginvegan.util.ProfileEditVeganTypeDialog
import com.google.android.material.tabs.TabLayoutMediator

class MainProfileFragment : BaseFragment<FragmentMainProfileBinding>(FragmentMainProfileBinding::bind, R.layout.fragment_main_profile
    ),BottomSheetLogoutFragment.MyFragmentInteractionListener,
    ProfileEditNameDialog.EditNameDialogListener,
    ProfileEditVeganTypeDialog.EditVeganTypeDialogListener{
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
        //시작 시 유저 이름 반영
        binding.tvUsername.text = ApplicationClass.xAuth.name

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

    //닉네임 수정
    private fun openEditUserNameDialog(){ //dialog 띄우기
        val editNameDialog = ProfileEditNameDialog(requireContext(), binding.tvUsername.text.toString())
        editNameDialog.setListener(this)
        editNameDialog.show()
    }
    override fun editNameOnSaveClicked(name: String) { //수정한 name UI 반영
        ApplicationClass.xAuth.name = name //이름 변경
        binding.tvUsername.text = name
    }

    //비건 유형 수정
    private fun openEditVeganTypeDialog(){ //dialog 띄우기
        val editVeganTypeDialog = ProfileEditVeganTypeDialog(requireContext(), binding.tvUserVeganType.text.toString())
        editVeganTypeDialog.setListener(this)
        editVeganTypeDialog.show()
    }
    override fun editVeganTypeOnSaveClicked(type:String){
        binding.tvUserVeganType.text = type
    }

    //로그아웃
    private fun openBottomSheetLogout(){ //bottom sheet 열기
        val bottomSheet =BottomSheetLogoutFragment()
        bottomSheet.listener = this
        bottomSheet.show(requireActivity().supportFragmentManager, null)
    }
    fun openLogoutDialog() { //dialog 띄우기
        val logoutDialog = LogoutDialog(requireContext())
        logoutDialog.show()
    }
    override fun onButtonClicked() { //dialog 로그아웃 버튼 클릭
        openLogoutDialog()
    }



}