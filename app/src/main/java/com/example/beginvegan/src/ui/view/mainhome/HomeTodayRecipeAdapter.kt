package com.example.beginvegan.src.ui.view.mainhome

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeTodayRecipeAdapter(mainHomeFragment: MainHomeFragment): FragmentStateAdapter(mainHomeFragment) {
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