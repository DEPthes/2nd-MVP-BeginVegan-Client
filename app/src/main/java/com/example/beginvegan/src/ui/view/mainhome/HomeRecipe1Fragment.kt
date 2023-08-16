package com.example.beginvegan.src.ui.view.mainhome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentHomeRecipe1Binding
import com.example.beginvegan.src.ui.view.MainActivity

class HomeRecipe1Fragment : BaseFragment<FragmentHomeRecipe1Binding>(
    FragmentHomeRecipe1Binding::bind,R.layout.fragment_home_recipe_1) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRecipe1.setOnClickListener {
            (activity as MainActivity).goRecipe()
        }

//        binding.tvRecipeTitle.setText()
//        binding.tvRecipeVeganType.setText()
//        binding.tvRecipeDescription.setText()
    }
}