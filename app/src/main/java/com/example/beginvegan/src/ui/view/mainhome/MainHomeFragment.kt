package com.example.beginvegan.src.ui.view.mainhome

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentMainHomeBinding
import com.example.beginvegan.src.ui.view.mainhome.HomeMagazineAdapter

class MainHomeFragment : BaseFragment<FragmentMainHomeBinding>(
    FragmentMainHomeBinding::bind,R.layout.fragment_main_home ){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //추천 레스토랑
        initializeViews()

        //오늘의 추천 레시피
        val vpTodayRecipe = binding.vpTodayRecipe
        val homeTodayRecipeAdapter = HomeTodayRecipeAdapter(this)
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

        //비건 매거진
        val vpMagazines = binding.vpMagazines
        val homeMagazineAdapter = HomeMagazineAdapter(this)
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
        val adapter = HomeRecommendRestAdapter{
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
}