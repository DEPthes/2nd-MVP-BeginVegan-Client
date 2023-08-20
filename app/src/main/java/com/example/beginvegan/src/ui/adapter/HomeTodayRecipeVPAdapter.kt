package com.example.beginvegan.src.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.beginvegan.src.data.model.recipe.RecipeThree
import com.example.beginvegan.src.ui.view.mainhome.HomeRecipe0Fragment
import com.example.beginvegan.src.ui.view.mainhome.HomeRecipe1Fragment
import com.example.beginvegan.src.ui.view.mainhome.HomeRecipe2Fragment
import com.example.beginvegan.src.ui.view.mainhome.MainHomeFragment

class HomeTodayRecipeVPAdapter(mainHomeFragment: MainHomeFragment, private val list:List<RecipeThree>): FragmentStateAdapter(mainHomeFragment) {

    val fragmentList = listOf<Fragment>(
        HomeRecipe0Fragment(list[0]),
        HomeRecipe1Fragment(list[1]),
        HomeRecipe2Fragment(list[2])
    )

    override fun getItemCount(): Int { return fragmentList.size }

    override fun createFragment(position: Int): Fragment {
//        val fragment = fragmentList[position]
//        fragment.getData

        return fragmentList[position]
    }
}