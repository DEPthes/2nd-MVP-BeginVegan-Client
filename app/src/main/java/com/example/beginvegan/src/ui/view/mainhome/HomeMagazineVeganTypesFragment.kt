package com.example.beginvegan.src.ui.view.mainhome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentHomeMagazineVeganDefineBinding
import com.example.beginvegan.databinding.FragmentHomeMagazineVeganTypesBinding

class HomeMagazineVeganTypesFragment : BaseFragment<FragmentHomeMagazineVeganTypesBinding>(
    FragmentHomeMagazineVeganTypesBinding::bind,R.layout.fragment_home_magazine_vegan_types ) {

    override fun init() {
        binding.btnMagazineTypes.setOnClickListener {
            val homeFragment = parentFragment as MainHomeFragment
            homeFragment.onDialogBtnClicked()
        }
    }
}