package com.example.beginvegan.src.ui.view

import android.util.Log
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentRestaurantDetailBinding
import com.example.beginvegan.src.data.model.restaurant.RestaurantDetailResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantInterface
import com.example.beginvegan.src.data.model.restaurant.RestaurantReviewResponse
import com.example.beginvegan.src.ui.adapter.RestaurantDetailReviewRVAdapter

class RestaurantDetailFragment : BaseFragment<FragmentRestaurantDetailBinding>(
    FragmentRestaurantDetailBinding::bind,
    R.layout.fragment_restaurant_detail
),RestaurantInterface{
    private lateinit var reViewList: ArrayList<String>

    override fun init() {
        // Res 아이디 받아오기

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

        // 페이징 처리
        binding.rvRestaurantReviews.addOnScrollListener(object: RecyclerView.OnScrollListener(){
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
        binding.btnReviewGoWrite.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fl_main,WriteReviewFragment()).addToBackStack(null).commit()
        }
        // callback Listener Review
        parentFragmentManager.setFragmentResultListener("requestKey",viewLifecycleOwner) { requestKey, bundle ->
            val result = bundle.getString("review")
            Log.d("setFragmentResultListener",result.toString())
        }

    }
    override fun onGetRestaurantDetailSuccess(response: RestaurantDetailResponse) {
        dismissLoadingDialog()
    }
    override fun onGetRestaurantDetailFailure(message: String) {}
    override fun onGetRestaurantReviewSuccess(response: RestaurantReviewResponse) {}
    override fun onGetRestaurantReviewFailure(message: String) {}
}