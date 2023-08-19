package com.example.beginvegan.src.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.beginvegan.src.ui.view.mainhome.HomeRecipe0Fragment
import com.example.beginvegan.src.ui.view.mainhome.HomeRecipe1Fragment
import com.example.beginvegan.src.ui.view.mainhome.HomeRecipe2Fragment
import com.example.beginvegan.src.ui.view.mainhome.MainHomeFragment

class HomeTodayRecipeVPAdapter(mainHomeFragment: MainHomeFragment): FragmentStateAdapter(mainHomeFragment) {

    val fragmentList = listOf<Fragment>(
        HomeRecipe0Fragment(),
        HomeRecipe1Fragment(),
        HomeRecipe2Fragment()
    )

    override fun getItemCount(): Int { return fragmentList.size }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}