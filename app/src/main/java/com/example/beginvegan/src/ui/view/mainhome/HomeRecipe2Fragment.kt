package com.example.beginvegan.src.ui.view.mainhome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentHomeRecipe2Binding
import com.example.beginvegan.src.ui.view.MainActivity

class HomeRecipe2Fragment : BaseFragment<FragmentHomeRecipe2Binding>(
    FragmentHomeRecipe2Binding::bind,R.layout.fragment_home_recipe_2) {

    override fun init() {
        binding.btnRecipe2.setOnClickListener {
            (activity as MainActivity).goRecipe(2)
        }

//        binding.tvRecipeTitle.setText()
//        binding.tvRecipeVeganType.setText()
//        binding.tvRecipeDescription.setText()
    }
}