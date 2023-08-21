package com.example.beginvegan.src.ui.view.mainhome

import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentHomeRecipe0Binding
import com.example.beginvegan.src.data.model.recipe.RecipeThree
import com.example.beginvegan.src.ui.view.MainActivity
import com.example.beginvegan.util.VeganType

class HomeRecipe0Fragment(private val data:RecipeThree) : BaseFragment<FragmentHomeRecipe0Binding>(
    FragmentHomeRecipe0Binding::bind,R.layout.fragment_home_recipe_0) {

    override fun init() {
        binding.btnRecipe0.setOnClickListener {
            (activity as MainActivity).goRecipe(data.id)
        }

        binding.tvRecipeTitle.text = data.name
        binding.tvRecipeVeganType.text = VeganType.valueOf(data.veganType).veganType
        binding.tvRecipeDescription.text = data.description
    }
}