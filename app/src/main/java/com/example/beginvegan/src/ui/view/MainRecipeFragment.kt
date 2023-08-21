package com.example.beginvegan.src.ui.view

import android.content.res.ColorStateList
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainRecipeBinding
import com.example.beginvegan.src.data.model.recipe.Recipe
import com.example.beginvegan.src.data.model.recipe.RecipeDetailResponse
import com.example.beginvegan.src.data.model.recipe.RecipeInterface
import com.example.beginvegan.src.data.model.recipe.RecipeList
import com.example.beginvegan.src.data.model.recipe.RecipeListResponse
import com.example.beginvegan.src.data.model.recipe.RecipeService
import com.example.beginvegan.src.data.model.recipe.RecipeThreeResponse
import com.example.beginvegan.src.ui.adapter.RecipeListRVAdapter
import com.example.beginvegan.src.ui.view.vegantest.TestQuestionMilkFragment
import com.example.beginvegan.util.RecipeDetailDialog
import com.google.android.material.chip.Chip
import kotlin.properties.Delegates

class MainRecipeFragment : BaseFragment<FragmentMainRecipeBinding>(
    FragmentMainRecipeBinding::bind, R.layout.fragment_main_recipe),
    RecipeInterface{

    private lateinit var recipeList: List<RecipeList>
    private var selectedRecipeId:Int? = null
    override fun init() {
        selectedRecipeId = arguments?.getInt("recipeId")
        Log.d("TAG", "init: in Fragment $selectedRecipeId")
        if (selectedRecipeId!=null) {
            Log.d("TAG", "init: id not null")
            onDialogBtnClicked(selectedRecipeId!!)
        }

        //Service
        RecipeService(this).tryGetRecipeList()

        //filter
//        binding.cgRecipeFilters.setOnCheckedChangeListener{group, checkedId ->
//            val checkedChip: Chip = binding.root.findViewById(checkedId)
//            checkFilter(checkedChip, checkedChip.isChecked)
//        }
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
        val recipeAdapter = RecipeListRVAdapter(recipeList)
        Log.d("TAG", "initializeViews: before")
        binding.rvRecipes.adapter = recipeAdapter
        binding.rvRecipes.layoutManager = GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL,false)

        //레시피 상세 페이지
        recipeAdapter.setOnItemClickListener(object: RecipeListRVAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: Recipe, position: Int) {
                onDialogBtnClicked(data.id)
            }
        })
    }

    //필터 선택시 UI 반영
    private fun checkFilter(filter:Chip, checked:Boolean){
        if(checked){
            filter.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.color_primary3))
            filter.setTextColor(ContextCompat.getColor(requireContext(),R.color.color_white))

            //필터
//            initializeViews(filter.text.toString())
        }else{
            filter.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.color_white))
            filter.setTextColor(ContextCompat.getColor(requireContext(),R.color.color_primary3))
        }
    }

    //recipe Dialog
    fun onDialogBtnClicked(id:Int){
        RecipeService(this).tryPostRecipeDetail(id)
        Log.d("TAG", "onDialogBtnClicked: dialog")
    }
    //인스턴스
//    companion object{
//        fun newInstance(): MainRecipeFragment {
//            return MainRecipeFragment()
//        }
//    }

    //서버 - 레시피
    override fun onGetRecipeListSuccess(response: RecipeListResponse) { //전체 레시피 목록 조회
//        response.information
//        val newRecipeLists=listOf(response.information[0])
        recipeList = listOf()
        recipeList = response.information

        //레시피 리스트
        initializeViews()
        Log.d("TAG", "onGetRecipeListSuccess: ")
    }
    override fun onGetRecipeListFailure(message: String) {
        Log.d("TAG", "onGetRecipeListFailure: $message")
    }
    override fun onPostRecipeDetailSuccess(response: RecipeDetailResponse) { //레시피 상세 정보 조회
        val dialog = RecipeDetailDialog(requireContext(), response.information)
        dialog.show()
        Log.d("TAG", "onPostRecipeDetailSuccess: ")
    }
    override fun onPostRecipeDetailFailure(message: String) {
        Log.d("TAG", "onPostRecipeDetailFailure: $message")
    }
    override fun onGetThreeRecipeListSuccess(response: RecipeThreeResponse) { }
    override fun onGetThreeRecipeListFailure(message: String) { }
}