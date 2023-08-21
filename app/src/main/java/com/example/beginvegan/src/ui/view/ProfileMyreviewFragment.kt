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
import com.example.beginvegan.src.data.model.review.ReviewDetail
import com.example.beginvegan.src.data.model.review.ReviewInterface
import com.example.beginvegan.src.data.model.review.ReviewListResponse
import com.example.beginvegan.src.data.model.review.ReviewService
import com.example.beginvegan.src.data.model.review.WriteReviewResponse
import com.example.beginvegan.src.ui.adapter.ProfileMyReviewRVAdapter
import com.example.beginvegan.src.ui.adapter.RecipeListRVAdapter

class ProfileMyreviewFragment : BaseFragment<FragmentProfileMyreviewBinding>(
    FragmentProfileMyreviewBinding::bind, R.layout.fragment_profile_myreview
    ),ReviewInterface {
    private lateinit var reviewList: ArrayList<ReviewDetail>
    private var pageNo = 0
    private var checkLast: Boolean = false
    private lateinit var reviewAdapter: ProfileMyReviewRVAdapter
    val TAG = "review"
    override fun init() {
        ReviewService(this).tryGetReviewList(pageNo)
        reviewList = arrayListOf()
        // 페이징 처리
        binding.rvMyreview.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter?.itemCount?.minus(1)
                if (lastVisibleItemPosition == itemTotalCount&&!checkLast) {
                    if(reviewList.size>9){
                        showLoadingDialog(requireContext())
                        pageNo++
                        ReviewService(this@ProfileMyreviewFragment).tryGetReviewList(pageNo)
                    }
                }
            }
        })
    }
    //서버 - 리뷰
    override fun onPostWriteReviewSuccess(response: WriteReviewResponse) { }
    override fun onPostWriteReviewFailure(message: String) { }
    override fun onGetReviewListSuccess(response: ReviewListResponse) {
        reviewList = arrayListOf()
        Log.d(TAG, "onGetReviewListSuccess: ${response.information.review} ")
        for (i: Int in 0 until response.information.review.size) {
            reviewList.add(response.information.review[i])
        }
        reviewAdapter = ProfileMyReviewRVAdapter(reviewList)
        binding.rvMyreview.adapter = reviewAdapter
        binding.rvMyreview.layoutManager = LinearLayoutManager(this.context)
//        reviewList = response.information
    }

    override fun onGetReviewListAddSuccess(response: ReviewListResponse) {
        if(response.information.review.isNotEmpty()){
            for (i: Int in 0 until response.information.review.size) {
                reviewList.add(response.information.review[i])
            }
            binding.rvMyreview.adapter!!.notifyItemRangeChanged(0,reviewList.size)
        }else{
            checkLast = true
        }
        dismissLoadingDialog()
    }

    override fun onGetReviewListFailure(message: String) {
        Log.d(TAG, "onGetReviewListFailure: $message")
    }
}