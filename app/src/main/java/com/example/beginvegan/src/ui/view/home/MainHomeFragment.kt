package com.example.beginvegan.src.ui.view.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
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
import com.example.beginvegan.src.ui.adapter.home.HomeMagazineVPAdapter
import com.example.beginvegan.src.ui.adapter.home.HomeRecommendRestRVAdapter
import com.example.beginvegan.src.ui.adapter.home.HomeTodayRecipeVPAdapter
import com.example.beginvegan.src.ui.view.main.MainActivity
import com.example.beginvegan.src.ui.view.map.VeganMapFragment
import com.example.beginvegan.src.ui.view.recipe.MainRecipeFragment
import com.example.beginvegan.util.Constants.HOME_TODAY_RECIPE_TO_RECIPE
import com.example.beginvegan.util.Constants.RECOMMENDED_POSITION
import com.example.beginvegan.util.Constants.RECOMMENDED_RESTAURANT
import com.example.beginvegan.util.Constants.TODAY_RECIPE
import com.example.beginvegan.util.HomeMagazineDetailDialog

class MainHomeFragment : BaseFragment<FragmentMainHomeBinding>(
    FragmentMainHomeBinding::bind, R.layout.fragment_main_home
),
    RecipeInterface, RestaurantFindInterface, MagazineInterface {

    private lateinit var todayRecipeList: List<RecipeThree>
    private lateinit var recommendRestList: ArrayList<NearRestaurant>
    private lateinit var homeRecommendRestRVAdapter: HomeRecommendRestRVAdapter
    private lateinit var homeRecommendTodayRecipeVPAdapter: HomeTodayRecipeVPAdapter

    override fun init() {
        showLoadingDialog(requireContext())
        val coordinate = Coordinate(ApplicationClass.xLatitude, ApplicationClass.xLongitude) //식당
        RestaurantFindService(this).tryPostFindRestaurant(coordinate)
        RecipeService(this).tryGetThreeRecipeList() //레시피
        MagazineService(this).tryGetMagazineTwoList() //매거진
        //유저 이름
        binding.tvSloganGreeting.text = "${ApplicationClass.xAuth.name}님, 안녕하세요!"
    }

    //비건 매거진 ViewPager
    private fun setMagazineVPAdapter(magazineList: List<Magazine>) {
        val vpMagazines = binding.vpMagazines
        val homeMagazineAdapter = HomeMagazineVPAdapter(this, magazineList)
        vpMagazines.adapter = homeMagazineAdapter
        binding.ciMagazineContents.setViewPager(binding.vpMagazines)
    }

    //매거진 Dialog
    fun onDialogBtnClicked(id: Int) {
        MagazineService(this).tryPostMagazineDetail(id) //매거진 상세 정보 호출
    }

    //오늘의 추천 레시피 ViewPager
    private fun setRecipeVPAdapter() {
        homeRecommendTodayRecipeVPAdapter = HomeTodayRecipeVPAdapter(todayRecipeList)
        binding.vpTodayRecipe.adapter = homeRecommendTodayRecipeVPAdapter
        binding.ciTodayRecipe.setViewPager(binding.vpTodayRecipe)
        onClickRecommendRecipe()
    }

    //오늘의 레시피
    override fun onGetThreeRecipeListSuccess(response: RecipeThreeResponse) {
        todayRecipeList = listOf(
            response.information[0], response.information[1], response.information[2]
        )
        setRecipeVPAdapter()
    }

    // 오늘의 추천 레시피 클릭시 레시피로 이동
    private fun onClickRecommendRecipe() {
        homeRecommendTodayRecipeVPAdapter.setOnItemClickListener(object :
            HomeTodayRecipeVPAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: RecipeThree, position: Int) {
                parentFragmentManager.setFragmentResult(
                    HOME_TODAY_RECIPE_TO_RECIPE, bundleOf(
                        TODAY_RECIPE to data
                    )
                )
                (activity as MainActivity).setActiveBottomNavigationItem(R.id.item_recipe)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fl_main, MainRecipeFragment())
                    .commit()
            }
        })
    }

    //추천 식당 recyclerView
    private fun setRestaurantRVAdapter() {
        homeRecommendRestRVAdapter = HomeRecommendRestRVAdapter(requireContext(), recommendRestList)
        val recyclerView = binding.rvHomeRecommendRestaurant
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
        recyclerView.adapter = homeRecommendRestRVAdapter
        val startPosition = Int.MAX_VALUE / 2
        recyclerView.scrollToPosition(startPosition)
        dismissLoadingDialog()
        onClickRecommendRestaurant()
    }

    // 오늘의 추천 식당 클릭시 비건 맵으로 이동
    private fun onClickRecommendRestaurant() {
        homeRecommendRestRVAdapter.setOnItemClickListener(object :
            HomeRecommendRestRVAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: NearRestaurant, position: Int) {
//                parentFragmentManager.setFragmentResult(
//                    RECOMMENDED_RESTAURANT,
//                    bundleOf(RECOMMENDED_RESTAURANT to data)
//                )
                val bundle = Bundle().apply {
                    putSerializable(RECOMMENDED_RESTAURANT, data)
                }
                (activity as MainActivity).setActiveBottomNavigationItem(R.id.item_map)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fl_main, VeganMapFragment().apply {
                        arguments = bundle
                    })
                    .commit()
            }
        })
    }

    //추천 식당
    override fun onPostFindRestaurantSuccess(response: RestaurantFindResponse) {
        recommendRestList = arrayListOf()
        if (response.information.isNotEmpty()) {
            val range = 0 until response.information.size
            val randomNums = arrayListOf<Int>()
            //난수 생성
            for (i: Int in 0..5) {
                var randomNum: Int = range.random()
                if (randomNum in randomNums) {
                    randomNum = range.random()
                    if (randomNum !in randomNums) {
                        randomNums.add(randomNum)
                    }
                } else {
                    randomNums.add(randomNum)
                }
            }
            for (i: Int in 0 until randomNums.size) {
                recommendRestList.add(response.information[randomNums[i]])
            }
            setRestaurantRVAdapter()
        }
    }

    //서버 - 매거진
    override fun onGetMagazineTwoListSuccess(response: MagazineTwoResponse) { //fragment ViewPager 띄우기
        val magazineList = listOf(response.information[0], response.information[1])
        setMagazineVPAdapter(magazineList)
    }

    override fun onPostMagazineDetailSuccess(response: MagazineDetailResponse) { //매거진 상세페이지
        val dialog = HomeMagazineDetailDialog(requireContext(), response.information)
        dialog.show()
    }

    //    override fun onGetUserFailure(message: String) { }
    //서버 - 레시피
    override fun onGetRecipeListSuccess(response: RecipeListResponse) {}
    override fun onGetRecipeListFailure(message: String) {}
    override fun onGetThreeRecipeListFailure(message: String) {}
    override fun onPostRecipeDetailSuccess(response: RecipeDetailResponse) {}
    override fun onPostRecipeDetailFailure(message: String) {}

    //서버 - 식당
    override fun onPostFindRestaurantFailure(message: String) {}

    //서버 - 매거진
    override fun onGetMagazineTwoListFailure(message: String) {}
    override fun onPostMagazineDetailFailure(message: String) {}
}