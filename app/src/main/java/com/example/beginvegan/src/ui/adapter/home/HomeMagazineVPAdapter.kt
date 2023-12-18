package com.example.beginvegan.src.ui.adapter.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.beginvegan.src.data.model.magazine.Magazine
import com.example.beginvegan.src.ui.view.home.HomeMagazineVeganDefineFragment
import com.example.beginvegan.src.ui.view.home.HomeMagazineVeganTypesFragment
import com.example.beginvegan.src.ui.view.home.MainHomeFragment

class HomeMagazineVPAdapter(mainHomeFragment: MainHomeFragment,magazineList:List<Magazine>): FragmentStateAdapter(mainHomeFragment) {

    private val fragmentList = listOf<Fragment>(
        HomeMagazineVeganDefineFragment(magazineList[0]),
        HomeMagazineVeganTypesFragment(magazineList[1])
    )

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}