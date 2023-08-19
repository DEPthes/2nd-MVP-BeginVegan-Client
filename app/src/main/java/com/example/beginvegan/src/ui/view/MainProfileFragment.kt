package com.example.beginvegan.src.ui.view

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainProfileBinding
import com.example.beginvegan.src.ui.adapter.ProfileMyRecordsVPAdapter
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
    }

    //닉네임 수정 dialog
    private fun openEditUserNameDialog(){
        val dialog = ProfileEditNameDialog(requireContext())
        dialog.show()
    }
    //Vegan Type 수정 dialog
    private fun openEditVeganTypeDialog(){
        val array = arrayOf("vegan","lacto","lacto-ovo")
        var checkedItemPosition = 0
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_profile_eidt_vegan_type,null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setSingleChoiceItems(array, checkedItemPosition) { dialog, which ->
            checkedItemPosition = which
            val radioButton = customView.findViewById<RadioButton>(android.R.id.text1)
            radioButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_primary3))
        }
        builder.setPositiveButton(getString(R.string.btn_confirm)) { dialog, which ->
            Log.d("TAG", "onClick: Confirm")
        }
        builder.setNegativeButton(getString(R.string.btn_cancel)) { dialog, which ->
            Log.d("TAG", "onClick: Cancel")
        }

        val dialog = builder.create()
        dialog.setOnShowListener{
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                ContextCompat.getColor(requireContext(),R.color.color_text_32))
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                ContextCompat.getColor(requireContext(),R.color.color_text_32))
        }
        dialog.show()
    }
}