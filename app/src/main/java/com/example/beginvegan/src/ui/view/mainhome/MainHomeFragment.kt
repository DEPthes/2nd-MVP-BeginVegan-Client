package com.example.beginvegan.src.ui.view.mainhome

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainHomeBinding
import com.example.beginvegan.src.data.model.recipe.Recipe
import com.example.beginvegan.src.data.model.recipe.RecipeDetailResponse
import com.example.beginvegan.src.data.model.recipe.RecipeInterface
import com.example.beginvegan.src.data.model.recipe.RecipeListResponse
import com.example.beginvegan.src.data.model.recipe.RecipeService
import com.example.beginvegan.src.data.model.recipe.RecipeThree
import com.example.beginvegan.src.data.model.recipe.RecipeThreeResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantDetailResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantInterface
import com.example.beginvegan.src.data.model.restaurant.RestaurantReviewResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantService
import com.example.beginvegan.src.ui.adapter.HomeMagazineVPAdapter
import com.example.beginvegan.src.ui.adapter.HomeRecommendRestRVAdapter
import com.example.beginvegan.src.ui.adapter.HomeTodayRecipeVPAdapter

class MainHomeFragment : BaseFragment<FragmentMainHomeBinding>(
    FragmentMainHomeBinding::bind,R.layout.fragment_main_home ),
    RecipeInterface{

//    lateinit var todayRecipeList: List<RecipeThree>

    override fun init() {
        //서버 데이터 불러오기
//        RestaurantService(this).tryGetRestaurantDetail()
        RecipeService(this)

        //추천 레스토랑 RecyclerView
        initializeViews()

        //오늘의 추천 레시피 ViewPager
        val vpTodayRecipe = binding.vpTodayRecipe
        val homeTodayRecipeAdapter = HomeTodayRecipeVPAdapter(this)
        vpTodayRecipe.adapter = homeTodayRecipeAdapter

        vpTodayRecipe.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> { binding.ivTodayRecipeIndicator0.setImageResource(R.drawable.shape_circle_home_indicator_active)
                        binding.ivTodayRecipeIndicator1.setImageResource(R.drawable.shape_circle_home_indicator_inactive)
                        binding.ivTodayRecipeIndicator2.setImageResource(R.drawable.shape_circle_home_indicator_inactive)
                    }
                    1 -> { binding.ivTodayRecipeIndicator0.setImageResource(R.drawable.shape_circle_home_indicator_inactive)
                        binding.ivTodayRecipeIndicator1.setImageResource(R.drawable.shape_circle_home_indicator_active)
                        binding.ivTodayRecipeIndicator2.setImageResource(R.drawable.shape_circle_home_indicator_inactive)
                    }
                    2 -> { binding.ivTodayRecipeIndicator0.setImageResource(R.drawable.shape_circle_home_indicator_inactive)
                        binding.ivTodayRecipeIndicator1.setImageResource(R.drawable.shape_circle_home_indicator_inactive)
                        binding.ivTodayRecipeIndicator2.setImageResource(R.drawable.shape_circle_home_indicator_active)
                    }
                }
            }
        })

        //비건 매거진 ViewPager
        val vpMagazines = binding.vpMagazines
        val homeMagazineAdapter = HomeMagazineVPAdapter(this)
        vpMagazines.adapter = homeMagazineAdapter

        vpMagazines.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> { binding.ivMagazineIndicator0.setImageResource(R.drawable.shape_circle_home_indicator_active)
                        binding.ivMagazineIndicator1.setImageResource(R.drawable.shape_circle_home_indicator_inactive)
                    }
                    1 -> { binding.ivMagazineIndicator0.setImageResource(R.drawable.shape_circle_home_indicator_inactive)
                        binding.ivMagazineIndicator1.setImageResource(R.drawable.shape_circle_home_indicator_active)
                    }
                }
            }
        })
    }

    //추천 식당 recyclerView
    private fun initializeViews(){
        val recyclerView = binding.rvHomeRecommendRestaurant
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,true)
        val adapter = HomeRecommendRestRVAdapter(requireContext()){
            position ->
            //로직
        }
        recyclerView.adapter = adapter

        val startPosition = Int.MAX_VALUE/2
        recyclerView.scrollToPosition(startPosition)
    }

    //매거진 Dialog
    fun onDialogBtnClicked(){
        val dialog = HomeMagazineDetailDialog(requireContext())
        dialog.show()
    }

    //오늘의 추천 레시피
//    fun getRecipeData(index:Int): RecipeThree{
//        return todayRecipeList[index]
//    }

    //서버 - 식당
//    override fun onGetRestaurantDetailSuccess(response: RestaurantDetailResponse) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onGetRestaurantDetailFailure(message: String) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onGetRestaurantReviewSuccess(response: RestaurantReviewResponse) {
//        TODO("Not yet implemented")
//    }
//    override fun onGetRestaurantReviewFailure(message: String) { }

    //서버 - 레시피
    override fun onGetRecipeListSuccess(response: List<RecipeListResponse>) { }
    override fun onGetRecipeListFailure(message: String) { }
    override fun onGetThreeRecipeListSuccess(response: List<RecipeThreeResponse>) {
//        todayRecipeList = listOf(
//            response[0].information, response[1].information, response[2].information
//        )
    }
    override fun onGetThreeRecipeListFailure(message: String) {
        Log.d("TAG", "onGetThreeRecipeListFailure: ")
    }
    override fun onPostRecipeDetailSuccess(response: RecipeDetailResponse) { }
    override fun onPostRecipeDetailFailure(message: String) { }
}