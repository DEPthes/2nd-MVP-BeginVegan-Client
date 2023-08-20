package com.example.beginvegan.src.ui.view.mainhome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentHomeRecipe2Binding
import com.example.beginvegan.src.data.model.recipe.RecipeThree
import com.example.beginvegan.src.ui.view.MainActivity
import com.example.beginvegan.util.VeganTypes

class HomeRecipe2Fragment(private val data: RecipeThree) : BaseFragment<FragmentHomeRecipe2Binding>(
    FragmentHomeRecipe2Binding::bind,R.layout.fragment_home_recipe_2) {

    override fun init() {
        binding.btnRecipe2.setOnClickListener {
            (activity as MainActivity).goRecipe(data.id)
        }
        binding.tvRecipeTitle.text = data.name
        binding.tvRecipeVeganType.text = VeganTypes.valueOf(data.veganType).veganType
        binding.tvRecipeDescription.text = data.description
    }
}