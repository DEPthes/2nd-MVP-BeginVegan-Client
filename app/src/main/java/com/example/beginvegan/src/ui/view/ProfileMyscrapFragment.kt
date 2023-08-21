package com.example.beginvegan.src.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentProfileMyreviewBinding
import com.example.beginvegan.databinding.FragmentProfileMyscrapBinding
import com.example.beginvegan.src.data.model.restaurant.Restaurant
import com.example.beginvegan.src.data.model.restaurant.RestaurantScrapInterface
import com.example.beginvegan.src.data.model.review.ReviewService
import com.example.beginvegan.src.data.model.user.UserScrapInterface
import com.example.beginvegan.src.data.model.user.UserScrapResponse
import com.example.beginvegan.src.data.model.user.UserScrapService
import com.example.beginvegan.src.ui.adapter.ProfileMyReviewRVAdapter
import com.example.beginvegan.src.ui.adapter.ProfileMyScrapRVAdapter
import kotlin.math.log

class ProfileMyscrapFragment : BaseFragment<FragmentProfileMyscrapBinding>(
    FragmentProfileMyscrapBinding::bind, R.layout.fragment_profile_myscrap
),UserScrapInterface {
    private lateinit var scrapList: ArrayList<Restaurant>
    private var pageNo = 0
    private var checkLast: Boolean = false
    private lateinit var scrapRVAdapter: ProfileMyScrapRVAdapter
    override fun init() {
        UserScrapService(this).tryGetUserBookmarks(pageNo)
        Log.d("TAG", "init: my scrap")
        scrapList = arrayListOf()
        // 페이징 처리
        binding.rvMyscrap.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter?.itemCount?.minus(1)
                if (lastVisibleItemPosition == itemTotalCount&&!checkLast) {
                    if(scrapList.size>9){
                        pageNo++
                        UserScrapService(this@ProfileMyscrapFragment).tryGetUserBookmarks(pageNo)
                    }
                }
            }
        })
    }

    override fun onGetUserBookmarksSuccess(response: UserScrapResponse) {
        Log.d("onGetUserBookmarksSuccess",response.information.toString())
        scrapList = arrayListOf()
        for (i: Int in 0 until response.information.restaurant.size) {
            scrapList.add(response.information.restaurant[i])
        }
        scrapRVAdapter = ProfileMyScrapRVAdapter(requireContext(),scrapList)
        binding.rvMyscrap.adapter = scrapRVAdapter
        binding.rvMyscrap.layoutManager = LinearLayoutManager(this.context)
    }

    override fun onGetUserBookmarksAddSuccess(response: UserScrapResponse) {
        Log.d("onGetUserBookmarksAddSuccess",response.information.toString())
        if(response.information.restaurant.isNotEmpty()){
            for (i: Int in 0 until response.information.restaurant.size) {
                scrapList.add(response.information.restaurant[i])
            }
            binding.rvMyscrap.adapter!!.notifyItemRangeChanged(0,scrapList.size)
        }else{
            checkLast = true
        }
    }

    override fun onGetUserBookmarksFailure(message: String) {
        Log.d("onGetUserBookmarksFailure",message)
    }
}