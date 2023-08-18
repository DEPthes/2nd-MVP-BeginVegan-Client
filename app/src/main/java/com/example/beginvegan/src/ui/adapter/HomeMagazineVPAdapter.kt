package com.example.beginvegan.src.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.beginvegan.src.ui.view.mainhome.HomeMagazineVeganDefineFragment
import com.example.beginvegan.src.ui.view.mainhome.HomeMagazineVeganTypesFragment
import com.example.beginvegan.src.ui.view.mainhome.MainHomeFragment

class HomeMagazineVPAdapter(mainHomeFragment: MainHomeFragment): FragmentStateAdapter(mainHomeFragment) {

    val fragmentList = listOf<Fragment>(
        HomeMagazineVeganDefineFragment(),
        HomeMagazineVeganTypesFragment()
    )

    override fun getItemCount(): Int { return fragmentList.size }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}