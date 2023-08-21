package com.example.beginvegan.src.ui.view.mainhome

import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentHomeMagazineVeganTypesBinding
import com.example.beginvegan.src.data.model.magazine.Magazine

class HomeMagazineVeganTypesFragment(private val data:Magazine) : BaseFragment<FragmentHomeMagazineVeganTypesBinding>(
    FragmentHomeMagazineVeganTypesBinding::bind,R.layout.fragment_home_magazine_vegan_types ) {

    override fun init() {
        binding.tvMagazineTitleDefine.text = data.title

        binding.btnMagazineTypes.setOnClickListener {
            val homeFragment = parentFragment as MainHomeFragment
            homeFragment.onDialogBtnClicked(data.id)
        }
    }
}