package com.example.beginvegan.src.ui.view.mainhome

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.beginvegan.R
import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainHomeBinding
import com.example.beginvegan.src.data.model.magazine.Magazine
import com.example.beginvegan.src.data.model.magazine.MagazineDetailResponse
import com.example.beginvegan.src.data.model.magazine.MagazineInterface
import com.example.beginvegan.src.data.model.magazine.MagazineService
import com.example.beginvegan.src.data.model.magazine.MagazineTwoResponse
import com.example.beginvegan.src.data.model.recipe.RecipeDetailResponse
import com.example.beginvegan.src.data.model.recipe.RecipeInterface
import com.example.beginvegan.src.data.model.recipe.RecipeListResponse
import com.example.beginvegan.src.data.model.recipe.RecipeService
import com.example.beginvegan.src.data.model.recipe.RecipeThree
import com.example.beginvegan.src.data.model.recipe.RecipeThreeResponse
import com.example.beginvegan.src.data.model.restaurant.Coordinate
import com.example.beginvegan.src.data.model.restaurant.NearRestaurant
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindInterface
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantFindService
import com.example.beginvegan.src.data.model.user.UserCheckInterface
import com.example.beginvegan.src.data.model.user.UserCheckService
import com.example.beginvegan.src.data.model.user.UserResponse
import com.example.beginvegan.src.ui.adapter.HomeMagazineVPAdapter
import com.example.beginvegan.src.ui.adapter.HomeRecommendRestRVAdapter
import com.example.beginvegan.src.ui.adapter.HomeTodayRecipeVPAdapter
import com.example.beginvegan.util.HomeMagazineDetailDialog

class MainHomeFragment : BaseFragment<FragmentMainHomeBinding>(
    FragmentMainHomeBinding::bind,R.layout.fragment_main_home ),
    UserCheckInterface,RecipeInterface, RestaurantFindInterface, MagazineInterface{

    lateinit var todayRecipeList: List<RecipeThree>
    lateinit var recommendRestList: ArrayList<NearRestaurant>
    val TAG = "TAG"

    override fun init() {
        //서버 데이터 불러오기
        UserCheckService(this).tryGetUser() //유저
        val coordinate = Coordinate(ApplicationClass.xLatitude,ApplicationClass.xLongitude) //식당
        RestaurantFindService(this).tryPostFindRestaurant(coordinate)
        RecipeService(this).tryGetThreeRecipeList() //레시피
        MagazineService(this).tryGetMagazineTwoList() //매거진
    }

    //추천 식당 recyclerView
    private fun setRestaurantRVAdapter(){
        val recyclerView = binding.rvHomeRecommendRestaurant
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,true)
        recyclerView.adapter = HomeRecommendRestRVAdapter(requireContext(),recommendRestList)

        val startPosition = Int.MAX_VALUE/2
        recyclerView.scrollToPosition(startPosition)
    }
    //오늘의 추천 레시피 ViewPager
    private fun setRecipeVPAdapter(){
        val vpTodayRecipe = binding.vpTodayRecipe
        val homeTodayRecipeAdapter = HomeTodayRecipeVPAdapter(this, todayRecipeList)
        vpTodayRecipe.adapter = homeTodayRecipeAdapter
        //Indicator
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
    //비건 매거진 ViewPager
    private fun setMagazineVPAdapter(magazineList:List<Magazine>){
        val vpMagazines = binding.vpMagazines
        val homeMagazineAdapter = HomeMagazineVPAdapter(this,magazineList)
        vpMagazines.adapter = homeMagazineAdapter
        //Indicator
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
    fun onDialogBtnClicked(id:Int){
        //매거진 디테일 호출
//        MagazineService(this).tryPostMagazineDetail(id)
    }


    ///서버///
    //유저 이름 받아오기
    override fun onGetUserSuccess(response: UserResponse) {
        binding.tvSloganGreeting.text = "${response.name}님, 안녕하세요!"
    }
    //오늘의 레시피
    override fun onGetThreeRecipeListSuccess(response: RecipeThreeResponse) {
        todayRecipeList = listOf(
            response.information[0], response.information[1], response.information[2]
        )
        setRecipeVPAdapter()
    }
    //추천 식당
    override fun onPostFindRestaurantSuccess(response: RestaurantFindResponse) {
        recommendRestList = arrayListOf()
        val range = 0 until response.information.size
        val randomNums = arrayListOf<Int>()
        //난수 생성
        for(i:Int in 0..4){
            var randomNum = range.random()
            if(randomNum in randomNums){
                randomNum = range.random()
            }
            randomNums.add(randomNum)
        }
        for(i:Int in 0 until randomNums.size){
            recommendRestList.add(response.information[randomNums[i]])
        }
        Log.d(TAG, "onPostFindRestaurantSuccess: ")
        setRestaurantRVAdapter()
    }
    //서버 - 유저
    override fun onGetUserFailure(message: String) { }
    //서버 - 레시피
    override fun onGetRecipeListSuccess(response: RecipeListResponse) { }
    override fun onGetRecipeListFailure(message: String) { }
    override fun onGetThreeRecipeListFailure(message: String) { }
    override fun onPostRecipeDetailSuccess(response: RecipeDetailResponse) { }
    override fun onPostRecipeDetailFailure(message: String) { }
    //서버 - 식당
    override fun onPostFindRestaurantFailure(message: String) { }
    //서버 - 매거진
    override fun onGetMagazineTwoListSuccess(response: MagazineTwoResponse) {
        val magazineList = listOf(response.information[0], response.information[1])
        setMagazineVPAdapter(magazineList)
        Log.d("Magazine", "onGetMagazineTwoListSuccess: ")
    }
    override fun onGetMagazineTwoListFailure(message: String) {
        Log.d("Magazine", "onGetMagazineTwoListFailure: $message")
    }
    override fun onPostMagazineDetailSuccess(response: MagazineDetailResponse) {
        val dialog = HomeMagazineDetailDialog(requireContext(), response.information)
        dialog.show()
        Log.d("Magazine", "onPostMagazineDetailSuccess: ")
    }
    override fun onPostMagazineDetailFailure(message: String) {
        Log.d("Magazine", "onPostMagazineDetailFailure: $message")
    }
}