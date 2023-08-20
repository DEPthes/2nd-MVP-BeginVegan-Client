package com.example.beginvegan.src.ui.view.mainhome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentHomeRecipe0Binding
import com.example.beginvegan.src.ui.view.MainActivity

class HomeRecipe0Fragment : BaseFragment<FragmentHomeRecipe0Binding>(
    FragmentHomeRecipe0Binding::bind,R.layout.fragment_home_recipe_0) {

    override fun init() {
        binding.btnRecipe0.setOnClickListener {
            (activity as MainActivity).goRecipe(0)
        }
    }

    fun setData(title:String, type:String, description:String){
        binding.tvRecipeTitle.text = title
        binding.tvRecipeVeganType.text = type
        binding.tvRecipeDescription.text = description
    }
}