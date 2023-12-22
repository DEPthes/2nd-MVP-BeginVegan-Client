package com.example.beginvegan.src.ui.view.recipe

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainRecipeBinding
import com.example.beginvegan.src.data.model.recipe.RecipeDetailResponse
import com.example.beginvegan.src.data.model.recipe.RecipeInterface
import com.example.beginvegan.src.data.model.recipe.RecipeList
import com.example.beginvegan.src.data.model.recipe.RecipeListResponse
import com.example.beginvegan.src.data.model.recipe.RecipeService
import com.example.beginvegan.src.data.model.recipe.RecipeThree
import com.example.beginvegan.src.data.model.recipe.RecipeThreeResponse
import com.example.beginvegan.src.data.model.restaurant.NearRestaurant
import com.example.beginvegan.src.data.model.user.VeganType
import com.example.beginvegan.src.ui.adapter.recipe.RecipeListRVAdapter
import com.example.beginvegan.src.ui.view.main.MainActivity
import com.example.beginvegan.util.Constants
import com.example.beginvegan.util.Constants.HOME_TODAY_RECIPE_TO_RECIPE
import com.example.beginvegan.util.Constants.TODAY_RECIPE
import com.example.beginvegan.util.RecipeDetailDialog
import com.example.beginvegan.util.VeganTypes
import com.google.android.material.chip.Chip

class MainRecipeFragment : BaseFragment<FragmentMainRecipeBinding>(
    FragmentMainRecipeBinding::bind, R.layout.fragment_main_recipe
), RecipeInterface {

    private lateinit var recipeList: List<RecipeList>
    private lateinit var filterList: ArrayList<RecipeList>
    private var mContext: Context? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onDetach() {
        super.onDetach()
        mContext = null
    }
    override fun init() {
        // case 1-1: 비건 타입별 레시피로 이동 할 경우
        showLoadingDialog(mContext!!)
        RecipeService(this).tryGetRecipeList()

        for (i in 0 until binding.cgRecipeFilters.childCount) {
            val chip = binding.cgRecipeFilters.getChildAt(i) as Chip
            chip.setOnClickListener {
                if (chip.isChecked) {
                    setFilter(chip.tag.toString())
                } else {
                    initializeViews(recipeList)
                }
            }
        }

        // case 1-2: 오늘의 추천 레시피에서 하나를 골라 이동 할 경우
        parentFragmentManager.setFragmentResultListener(
            HOME_TODAY_RECIPE_TO_RECIPE,
            viewLifecycleOwner
        ) { _, bundle ->
            val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(TODAY_RECIPE, RecipeThree::class.java)
            } else {
                bundle.getSerializable(TODAY_RECIPE) as? RecipeThree
            }
            if (data != null) {
                RecipeService(this).tryPostRecipeDetail(data.id)
            }
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

    //필터
    private fun setFilter(veganType: String) {
        filterList = arrayListOf()
        for (i: Int in recipeList.indices) {
            if (recipeList[i].veganType == veganType) {
                filterList.add(recipeList[i])
            }
        }
        initializeViews(filterList)

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
        val dialog = RecipeDetailDialog(mContext!!, response.information)
        dialog.show()
    }

    override fun onGetRecipeListFailure(message: String) {}
    override fun onPostRecipeDetailFailure(message: String) {}
    override fun onGetThreeRecipeListSuccess(response: RecipeThreeResponse) {}
    override fun onGetThreeRecipeListFailure(message: String) {}
}