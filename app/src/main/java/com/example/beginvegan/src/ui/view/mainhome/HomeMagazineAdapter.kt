package com.example.beginvegan.src.ui.view.mainhome

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeMagazineAdapter(mainHomeFragment: MainHomeFragment): FragmentStateAdapter(mainHomeFragment) {

    val fragmentList = listOf<Fragment>(
        HomeMagazineVeganDefineFragment(),
        HomeMagazineVeganTypesFragment()
    )

    override fun getItemCount(): Int { return fragmentList.size }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}