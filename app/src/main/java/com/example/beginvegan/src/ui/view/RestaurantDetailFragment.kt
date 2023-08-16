package com.example.beginvegan.src.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentRestaurantDetailBinding
import com.example.beginvegan.databinding.FragmentVeganMapBinding
import com.example.beginvegan.src.ui.adapter.RestaurantDetailReviewRVAdapter
import com.example.beginvegan.src.ui.adapter.VeganMapBottomSheetRVAdapter

class RestaurantDetailFragment : BaseFragment<FragmentRestaurantDetailBinding>(
    FragmentRestaurantDetailBinding::bind,
    R.layout.fragment_restaurant_detail
) {
    private lateinit var dataList: ArrayList<String>
    override fun init() {
        dataList = arrayListOf()
        dataList.apply{
            add("hello1")
            add("hello2")
            add("hello3")
            add("hello4")
        }
        val dataRVAdapter  = RestaurantDetailReviewRVAdapter(dataList)
        binding.rvRestaurantReviews.adapter = dataRVAdapter
        binding.rvRestaurantReviews.layoutManager = LinearLayoutManager(this.context)
    }
}