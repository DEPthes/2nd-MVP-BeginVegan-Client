package com.example.beginvegan.src.ui.view.recipe

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainRecipeBinding
import com.example.beginvegan.src.data.model.recipe.RecipeDetailResponse
import com.example.beginvegan.src.data.model.recipe.RecipeInterface
import com.example.beginvegan.src.data.model.recipe.RecipeList
import com.example.beginvegan.src.data.model.recipe.RecipeListResponse
import com.example.beginvegan.src.data.model.recipe.RecipeService
import com.example.beginvegan.src.data.model.recipe.RecipeThreeResponse
import com.example.beginvegan.src.ui.adapter.recipe.RecipeListRVAdapter
import com.example.beginvegan.util.RecipeDetailDialog
import com.example.beginvegan.util.VeganTypes
import com.google.android.material.chip.Chip

class MainRecipeFragment : BaseFragment<FragmentMainRecipeBinding>(
    FragmentMainRecipeBinding::bind, R.layout.fragment_main_recipe
),
    RecipeInterface {

    private lateinit var recipeList: List<RecipeList>
    private lateinit var filterList: ArrayList<RecipeList>
    private var selectedRecipeId: Int? = null
    val TAG = "recipe"
    override fun init() {
        //Service
        showLoadingDialog(requireContext())
        RecipeService(this).tryGetRecipeList()

        //filter UI 적용
        binding.cgRecipeFilters.setOnCheckedChangeListener { _, checkedId ->
            val checkedChip: Chip = binding.root.findViewById(checkedId)
            checkFilter(checkedChip, checkedChip.isChecked)
        }

    }

    //레시피 리스트 RVAdapter
    private fun initializeViews(list: List<RecipeList>) {
        val recipeAdapter = RecipeListRVAdapter(list)
        binding.rvRecipes.adapter = recipeAdapter
        binding.rvRecipes.layoutManager =
            GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)

        //레시피 상세 페이지
        recipeAdapter.setOnItemClickListener(object : RecipeListRVAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: RecipeList, position: Int) {
                onDialogBtnClicked(data.id)
            }
        })
        dismissLoadingDialog()
    }

    //필터 선택시 UI 반영
    private fun checkFilter(filter: Chip, checked: Boolean) {
        if (checked) {
            setFilter(binding.cgRecipeFilters.indexOfChild(filter) - 1)
        }
    }

    //필터
    private fun setFilter(index: Int) {
        if (index == -1) {
            initializeViews(recipeList)
        } else {
            val enum = enumValues<VeganTypes>()
            val filter = enum[index].name
            filterList = arrayListOf()
            for (i: Int in 0 until recipeList.size) {
                if (recipeList[i].veganType == filter) {
                    filterList.add(recipeList[i])
                }
            }
            initializeViews(filterList)
        }
    }

    //레시피 상세 정보 Dialog 띄우기
    fun onDialogBtnClicked(id: Int) {
        RecipeService(this).tryPostRecipeDetail(id)
    }

    //서버 - 레시피
    override fun onGetRecipeListSuccess(response: RecipeListResponse) {
        recipeList = listOf()
        recipeList = response.information
        //레시피 리스트
        initializeViews(recipeList)
    }

    override fun onPostRecipeDetailSuccess(response: RecipeDetailResponse) { //레시피 상세 정보 조회
        val dialog = RecipeDetailDialog(requireContext(), response.information)
        dialog.show()
    }

    override fun onGetRecipeListFailure(message: String) {}
    override fun onPostRecipeDetailFailure(message: String) {}
    override fun onGetThreeRecipeListSuccess(response: RecipeThreeResponse) {}
    override fun onGetThreeRecipeListFailure(message: String) {}
}