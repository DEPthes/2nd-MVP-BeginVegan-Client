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
import com.example.beginvegan.src.data.model.restaurant.Coordinate
import com.example.beginvegan.src.data.model.restaurant.NearRestaurant
import com.example.beginvegan.src.data.model.restaurant.RestaurantDetailResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindInterface
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindService
import com.example.beginvegan.src.data.model.restaurant.RestaurantInterface
import com.example.beginvegan.src.data.model.restaurant.RestaurantReviewResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantService
import com.example.beginvegan.src.ui.adapter.HomeMagazineVPAdapter
import com.example.beginvegan.src.ui.adapter.HomeRecommendRestRVAdapter
import com.example.beginvegan.src.ui.adapter.HomeTodayRecipeVPAdapter

class MainHomeFragment : BaseFragment<FragmentMainHomeBinding>(
    FragmentMainHomeBinding::bind,R.layout.fragment_main_home ),
    RecipeInterface, RestaurantFindInterface{

    lateinit var todayRecipeList: List<RecipeThree>
    lateinit var recommendRestList: ArrayList<NearRestaurant>

    override fun onGetThreeRecipeListSuccess(response: RecipeThreeResponse) {
        todayRecipeList = listOf(
            response.information[0], response.information[1], response.information[2]
        )
        setRecipeVPAdapter()
    }
    override fun init() {
        //서버 데이터 불러오기
        val coordinate = Coordinate("37.580261","126.922838") //test
        RestaurantFindService(this).tryPostFindRestaurant(coordinate)
        RecipeService(this).tryGetThreeRecipeList()

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




    //매거진 Dialog
    fun onDialogBtnClicked(){
        val dialog = HomeMagazineDetailDialog(requireContext())
        dialog.show()
    }

    //오늘의 추천 레시피 ViewPager
    private fun setRecipeVPAdapter(){
        val vpTodayRecipe = binding.vpTodayRecipe
        val homeTodayRecipeAdapter = HomeTodayRecipeVPAdapter(this, todayRecipeList)
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
    }
    //추천 식당 recyclerView
    private fun setRestaurantRVAdapter(){
        val recyclerView = binding.rvHomeRecommendRestaurant
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,true)
        recyclerView.adapter = HomeRecommendRestRVAdapter(requireContext(),recommendRestList)

        val startPosition = Int.MAX_VALUE/2
        recyclerView.scrollToPosition(startPosition)
    }


    //서버 - 레시피
    override fun onGetRecipeListSuccess(response: RecipeListResponse) { }
    override fun onGetRecipeListFailure(message: String) { }
    override fun onGetThreeRecipeListFailure(message: String) { }
    override fun onPostRecipeDetailSuccess(response: RecipeDetailResponse) { }
    override fun onPostRecipeDetailFailure(message: String) { }
    //서버 - 식당
    override fun onPostFindRestaurantSuccess(response: RestaurantFindResponse) {
        val range = 0..response.information.size
        val randomNums = arrayListOf<Int>()
        //난수 생성
        while(randomNums.size <5){
            val randomNum = range.random()
            if(randomNum !in randomNums){
                randomNums.add(randomNum)
            }
        }
        for(i:Int in 1..5){
            recommendRestList.add(response.information[i])
        }
        setRestaurantRVAdapter()
        Log.d("TAG", "onPostFindRestaurantSuccess: ")
    }
    override fun onPostFIndRestaurantFailure(message: String) {
        Log.d("TAG", "onPostFIndRestaurantFailure: $message")
    }
}