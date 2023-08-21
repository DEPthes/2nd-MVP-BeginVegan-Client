package com.example.beginvegan.src.ui.view.mainhome

import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentHomeRecipe1Binding
import com.example.beginvegan.src.data.model.recipe.RecipeThree
import com.example.beginvegan.src.ui.view.MainActivity
import com.example.beginvegan.util.VeganTypes

class HomeRecipe1Fragment(private val data: RecipeThree) : BaseFragment<FragmentHomeRecipe1Binding>(
    FragmentHomeRecipe1Binding::bind,R.layout.fragment_home_recipe_1) {

    override fun init() {
//        binding.btnRecipe1.setOnClickListener {
//            (activity as MainActivity).goRecipe(data.id)
//        }
        binding.tvRecipeTitle.text = data.name
        binding.tvRecipeVeganType.text = VeganTypes.valueOf(data.veganType).veganType
        binding.tvRecipeDescription.text = data.description
    }
}