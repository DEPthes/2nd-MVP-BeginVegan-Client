package com.example.beginvegan.src.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentProfileMyreviewBinding
import com.example.beginvegan.src.data.model.review.Review
import com.example.beginvegan.src.data.model.review.ReviewInterface
import com.example.beginvegan.src.data.model.review.ReviewListResponse
import com.example.beginvegan.src.data.model.review.ReviewService
import com.example.beginvegan.src.data.model.review.WriteReviewResponse
import com.example.beginvegan.src.ui.adapter.ProfileMyReviewRVAdapter
import com.example.beginvegan.src.ui.adapter.RecipeListRVAdapter

class ProfileMyreviewFragment : BaseFragment<FragmentProfileMyreviewBinding>(
    FragmentProfileMyreviewBinding::bind, R.layout.fragment_profile_myreview
    ),ReviewInterface {
    private lateinit var reviewList: List<Review>
    val TAG = "tag"
    override fun init() {
        ReviewService(this).tryGetReviewList()
    }

    private fun initializeViews(){
        val reviewAdapter = ProfileMyReviewRVAdapter(reviewList)
        binding.rvMyreview.adapter = reviewAdapter
        binding.rvMyreview.layoutManager = LinearLayoutManager(this.context)

        reviewAdapter.setOnItemClickListener(object: ProfileMyReviewRVAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: Review, position: Int) {
                Log.d("TAG", "onItemClick: my review")
            }
        })
        // 페이징 처리
        binding.rvMyreview.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = 10
                if (lastVisibleItemPosition + 1 == itemTotalCount) {
                    // Last page next data load

                }
            }
        })
    }
    //서버 - 리뷰
    override fun onPostWriteReviewSuccess(response: WriteReviewResponse) { }
    override fun onPostWriteReviewFailure(message: String) { }
    override fun onGetReviewListSuccess(response: ReviewListResponse) {
        Log.d(TAG, "onGetReviewListSuccess: ")
        reviewList = listOf()
        reviewList = response.information
        initializeViews()
    }
    override fun onGetReviewListFailure(message: String) {
        Log.d(TAG, "onGetReviewListFailure: $message")
    }
}