package com.example.beginvegan.src.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentRestaurantDetailBinding
import com.example.beginvegan.databinding.FragmentVeganMapBinding
import com.example.beginvegan.src.data.model.restaurant.RestaurantDetailResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantInterface
import com.example.beginvegan.src.data.model.restaurant.RestaurantReviewResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantService
import com.example.beginvegan.src.ui.adapter.RestaurantDetailReviewRVAdapter
import com.example.beginvegan.src.ui.adapter.VeganMapBottomSheetRVAdapter

class RestaurantDetailFragment : BaseFragment<FragmentRestaurantDetailBinding>(
    FragmentRestaurantDetailBinding::bind,
    R.layout.fragment_restaurant_detail
),RestaurantInterface{
    private lateinit var reViewList: ArrayList<String>
    override fun init() {
        // Res 아이디 받아오기
        RestaurantService(this).tryGetRestaurantDetail(0).apply {
            showLoadingDialog(requireContext())
        }
        RestaurantService(this).tryGetRestaurantReview(0)

        reViewList = arrayListOf()
        reViewList.apply{
            add("hello1")
            add("hello2")
            add("hello3")
            add("hello4")
        }
        val dataRVAdapter  = RestaurantDetailReviewRVAdapter(reViewList)
        binding.rvRestaurantReviews.adapter = dataRVAdapter
        binding.rvRestaurantReviews.layoutManager = LinearLayoutManager(this.context)
    }

    override fun onGetRestaurantDetailSuccess(response: RestaurantDetailResponse) {
        dismissLoadingDialog()
    }

    override fun onGetRestaurantDetailFailure(message: String) {
    }

    override fun onGetRestaurantReviewSuccess(response: RestaurantReviewResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetRestaurantReviewFailure(message: String) {}


}