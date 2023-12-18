//package com.example.beginvegan.src.ui.view.home
//
//import com.example.beginvegan.R
//import com.example.beginvegan.config.BaseFragment
//import com.example.beginvegan.databinding.FragmentHomeRecipe2Binding
//import com.example.beginvegan.src.data.model.recipe.RecipeThree
//import com.example.beginvegan.util.VeganTypes
//// 나중에 이미지로 대체
//class HomeRecipe2Fragment(private val data: RecipeThree) : BaseFragment<FragmentHomeRecipe2Binding>(
//    FragmentHomeRecipe2Binding::bind,R.layout.fragment_home_recipe_2) {
//
//    override fun init() {
//        binding.tvRecipeTitle.text = data.name
//        binding.tvRecipeVeganType.text = VeganTypes.valueOf(data.veganType).veganType
//        binding.tvRecipeDescription.text = data.description
//    }
//}