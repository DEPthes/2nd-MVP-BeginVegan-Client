package com.example.beginvegan.src.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentProfileMyreviewBinding
import com.example.beginvegan.src.ui.adapter.ProfileMyReviewRVAdapter
import com.example.beginvegan.src.ui.adapter.RecipeListRVAdapter

class ProfileMyreviewFragment : BaseFragment<FragmentProfileMyreviewBinding>(
    FragmentProfileMyreviewBinding::bind, R.layout.fragment_profile_myreview
) {
    private lateinit var reviewList: ArrayList<String>
    override fun init() {
        Log.d("TAG", "init: my review")
        reviewList = arrayListOf()
        reviewList.apply {
            add("hello1")
            add("hello2")
            add("hello3")
            add("hello4")
        }

        initializeViews()
    }

    private fun initializeViews(){
        val reviewAdapter = ProfileMyReviewRVAdapter(reviewList)
        binding.rvMyreview.adapter = reviewAdapter
        binding.rvMyreview.layoutManager = LinearLayoutManager(this.context)

        reviewAdapter.setOnItemClickListener(object: ProfileMyReviewRVAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: String, position: Int) {
                Log.d("TAG", "onItemClick: my review")
            }
        })
    }
}