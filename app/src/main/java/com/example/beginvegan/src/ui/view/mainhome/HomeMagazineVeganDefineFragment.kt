package com.example.beginvegan.src.ui.view.mainhome

import android.util.Log
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentHomeMagazineVeganDefineBinding
import com.example.beginvegan.src.data.model.magazine.Magazine

class HomeMagazineVeganDefineFragment(private val data:Magazine) : BaseFragment<FragmentHomeMagazineVeganDefineBinding>(
    FragmentHomeMagazineVeganDefineBinding::bind,R.layout.fragment_home_magazine_vegan_define ) {

    override fun init() {
        binding.tvMagazineTitleDefine.text = data.title

        binding.btnMagazineDefine.setOnClickListener {
            val homeFragment = parentFragment as MainHomeFragment
            homeFragment.onDialogBtnClicked(data.id)
        }
    }

}