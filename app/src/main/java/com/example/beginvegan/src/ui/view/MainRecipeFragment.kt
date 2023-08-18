package com.example.beginvegan.src.ui.view

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainRecipeBinding
import com.example.beginvegan.src.ui.adapter.RecipeListRVAdapter
import com.google.android.material.chip.Chip

class MainRecipeFragment : BaseFragment<FragmentMainRecipeBinding>(
    FragmentMainRecipeBinding::bind, R.layout.fragment_main_recipe){

    private lateinit var recipeNames: ArrayList<String>
    override fun init() {
        recipeNames = arrayListOf()
        recipeNames.apply{
            add("hello1")
            add("hello2")
            add("hello3")
            add("hello4")
        }

        //레시피 리스트
        initializeViews()

        //filter
        binding.cFilterAll.setOnCheckedChangeListener{ _, isChecked ->
            checkFilter(binding.cFilterAll, isChecked)
        }
        binding.cFilterVegan.setOnCheckedChangeListener{ _, isChecked ->
            checkFilter(binding.cFilterVegan, isChecked)
        }
        binding.cFilterLacto.setOnCheckedChangeListener{ _, isChecked ->
            checkFilter(binding.cFilterLacto, isChecked)
        }
        binding.cFilterLactoOvo.setOnCheckedChangeListener{ _, isChecked ->
            checkFilter(binding.cFilterLactoOvo, isChecked)
        }
        binding.cFilterPesco.setOnCheckedChangeListener{ _, isChecked ->
            checkFilter(binding.cFilterPesco, isChecked)
        }
        binding.cFilterPollo.setOnCheckedChangeListener{ _, isChecked ->
            checkFilter(binding.cFilterPollo, isChecked)
        }
        binding.cFilterFlexitarian.setOnCheckedChangeListener{ _, isChecked ->
            checkFilter(binding.cFilterFlexitarian, isChecked)
        }
    }

    private fun initializeViews(){
        val recipeAdapter = RecipeListRVAdapter(recipeNames)
        Log.d("TAG", "initializeViews: before")
        binding.rvRecipes.adapter = recipeAdapter
        binding.rvRecipes.layoutManager = GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL,false)

        recipeAdapter.setOnItemClickListener(object: RecipeListRVAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: String, position: Int) {
                onDialogBtnClicked()
            }
        })
    }

    private fun checkFilter(filter:Chip, checked:Boolean){
        if(checked){
            filter.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.color_primary3))
            filter.setTextColor(ContextCompat.getColor(requireContext(),R.color.color_white))

            //필터

        }else{
            filter.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.color_white))
            filter.setTextColor(ContextCompat.getColor(requireContext(),R.color.color_primary3))
        }
    }


    //recipe Dialog
    fun onDialogBtnClicked(){
        val dialog = RecipeDetailDialog(requireContext())
        dialog.show()
    }
}