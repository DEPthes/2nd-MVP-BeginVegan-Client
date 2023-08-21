package com.example.beginvegan.src.ui.view

import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beginvegan.R
import com.example.beginvegan.config.BaseFragment
import com.example.beginvegan.databinding.FragmentRestaurantDetailBinding
import com.example.beginvegan.src.data.model.restaurant.NearRestaurant
import com.example.beginvegan.src.data.model.restaurant.RestaurantDetailResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantInterface
import com.example.beginvegan.src.data.model.restaurant.RestaurantReviewResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantScrapDeleteInterface
import com.example.beginvegan.src.data.model.restaurant.RestaurantScrapDeleteResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantScrapDeleteService
import com.example.beginvegan.src.data.model.restaurant.RestaurantScrapInterface
import com.example.beginvegan.src.data.model.restaurant.RestaurantScrapResponse
import com.example.beginvegan.src.data.model.restaurant.RestaurantScrapService
import com.example.beginvegan.src.data.model.restaurant.RestaurantService
import com.example.beginvegan.src.data.model.restaurant.ReviewDetail
import com.example.beginvegan.src.ui.adapter.RestaurantDetailReviewRVAdapter
import com.example.beginvegan.util.Constants.RESTAURANT_ID

class RestaurantDetailFragment : BaseFragment<FragmentRestaurantDetailBinding>(
    FragmentRestaurantDetailBinding::bind,
    R.layout.fragment_restaurant_detail
),RestaurantInterface,RestaurantScrapInterface,RestaurantScrapDeleteInterface{
    private lateinit var reViewList: ArrayList<ReviewDetail>
    private var pageNo = 0
    private lateinit var dataRVAdapter : RestaurantDetailReviewRVAdapter
    override fun init() {
        showLoadingDialog(requireContext())
        parentFragmentManager.setFragmentResultListener(RESTAURANT_ID,viewLifecycleOwner){ _, bundle ->
            val restaurantId = bundle.getInt(RESTAURANT_ID)
            Log.d("restaurantId",restaurantId.toString())
            RestaurantService(this).tryGetRestaurantDetail(restaurantId)
            RestaurantService(this).tryGetRestaurantReview(restaurantId!!.toInt(),pageNo)
            binding.ibRestaurantBookmarks.setOnClickListener {
                if(binding.ibRestaurantBookmarks.isPressed){
                    RestaurantScrapDeleteService(this).tryDeleteScrapRestaurant(restaurantId)
                }else{
                    RestaurantScrapService(this).tryPostScrapRestaurant(restaurantId)
                }

            }
        }


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
        Log.d("onGetRestaurantDetailSuccess",response.toString())
        Glide.with(requireContext()).load(response.information.restaurant.imageUrl).into(binding.ivRestaurantImg)
        Glide.with(requireContext()).load(response.information.menus[0].imageUrl).into(binding.ivMenuImg1)
        Glide.with(requireContext()).load(response.information.menus[1].imageUrl).into(binding.ivMenuImg2)
        binding.tvRestaurantAddress.text = response.information.restaurant.address.detailAddress
        if(response.information.restaurant.restaurantType == "RESTAURANT"){
            binding.tvRestaurantType.text = "식당"
        }
        if(response.information.restaurant.restaurantType == "CAFE"){
            binding.tvRestaurantType.text = "카페/디저트"
        }
        binding.tvRestaurantTime.text = response.information.restaurant.businessHours
        binding.tvCallNumber.text = response.information.restaurant.contactNumber
        binding.tvMenuName1.text = response.information.menus[0].name
        binding.tvMenuPrice1.text = response.information.menus[0].price
        binding.tvMenuDescription1.text = response.information.menus[0].description
        binding.tvMenuOrigin1.text = "이미지 출처: NAVER"
        binding.tvMenuName2.text = response.information.menus[1].name
        binding.tvMenuPrice2.text = response.information.menus[1].price
        binding.tvMenuDescription2.text = response.information.menus[1].description
        binding.tvMenuOrigin2.text = "이미지 출처: NAVER"
        dismissLoadingDialog()
    }
    override fun onGetRestaurantDetailFailure(message: String) {
        Log.d("onGetRestaurantDetailFailure",message)
    }
    override fun onGetRestaurantReviewSuccess(response: RestaurantReviewResponse) {
        Log.d("onGetRestaurantReviewSuccess",response.toString())
        reViewList = arrayListOf()
        for (i: Int in 0 until response.information.reviews.size) {
            reViewList.add(response.information.reviews[i])
        }
        binding.tvReviewCount.text = "방문자 리뷰 ${response.information.reviews.size}개"
        dataRVAdapter  = RestaurantDetailReviewRVAdapter(reViewList)
        binding.rvRestaurantReviews.adapter = dataRVAdapter
        binding.rvRestaurantReviews.layoutManager = LinearLayoutManager(this.context)
    }
    override fun onGetRestaurantReviewFailure(message: String) {
        Log.d("onGetRestaurantReviewFailure",message)
    }

    override fun onPostScrapRestaurantSuccess(response: RestaurantScrapResponse) {
        Toast.makeText(requireContext(),response.information.message,Toast.LENGTH_SHORT).show()
    }

    override fun onPostScrapRestaurantFailure(message: String) {
        Log.d("onPostScrapRestaurantFailure",message)
    }

    override fun onDeleteScrapRestaurantSuccess(response: RestaurantScrapDeleteResponse) {
        Toast.makeText(requireContext(),response.information.message,Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteScrapRestaurantFailure(message: String) {
        Log.d("onDeleteScrapRestaurantFailure",message)
    }

}