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
    private var checkLast: Boolean = false
    private var restaurantId= 0
    override fun init() {
        reViewList = arrayListOf()
        showLoadingDialog(requireContext())
        parentFragmentManager.setFragmentResultListener(RESTAURANT_ID,viewLifecycleOwner){ _, bundle ->
            restaurantId = bundle.getInt(RESTAURANT_ID)
            Log.d("restaurantId",restaurantId.toString())
            RestaurantService(this).tryGetRestaurantDetail(restaurantId)
            RestaurantService(this).tryGetRestaurantReview(restaurantId!!.toInt(),pageNo)
            binding.ibRestaurantBookmarks.setOnClickListener {
                showLoadingDialog(requireContext())
                if(binding.ibRestaurantBookmarks.isPressed){
                    RestaurantScrapService(this).tryPostScrapRestaurant(restaurantId)
                }else{
                    RestaurantScrapDeleteService(this).tryDeleteScrapRestaurant(restaurantId)
                }
            }
            // 페이징 처리
            binding.rvRestaurantReviews.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastVisibleItemPosition =
                        (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    val itemTotalCount = recyclerView.adapter?.itemCount?.minus(1)
                    if (lastVisibleItemPosition == itemTotalCount&&!checkLast) {
                        Log.d("lastVisibleItemPosition","Last Item")
                        showLoadingDialog(requireContext())
                        pageNo++
                        RestaurantService(this@RestaurantDetailFragment).tryGetRestaurantReview(restaurantId!!.toInt(),pageNo)
                    }
                }
            })
            dismissLoadingDialog()
        }

        dismissLoadingDialog()



        binding.btnReviewGoWrite.setOnClickListener {
            parentFragmentManager.setFragmentResult("review",bundleOf("review" to restaurantId))
            parentFragmentManager.beginTransaction().hide(this@RestaurantDetailFragment)
                .add(R.id.fl_main, WriteReviewFragment()).addToBackStack(null).commit()
        }
        // callback Listener Review
        parentFragmentManager.setFragmentResultListener("return",viewLifecycleOwner) { _, _ ->
            checkLast = false
            dismissLoadingDialog()
            pageNo=0
            RestaurantService(this).tryGetRestaurantReview(restaurantId!!.toInt(),pageNo)

        }
    }
    override fun onGetRestaurantDetailSuccess(response: RestaurantDetailResponse) {
        Log.d("onGetRestaurantDetailSuccess",response.toString())

        if (response.information.restaurant.imageUrl.isNullOrEmpty()) {
            Glide.with(requireContext()).load(R.drawable.test_res2)
                .into(binding.ivRestaurantImg)
        } else {
            Glide.with(requireContext()).load(response.information.restaurant.imageUrl).into(binding.ivRestaurantImg)
        }
        if (response.information.menus[0].imageUrl.isNullOrEmpty()) {
            Glide.with(requireContext()).load(R.drawable.test_res)
                .into(binding.ivMenuImg1)
        } else {
            Glide.with(requireContext()).load(response.information.menus[0].imageUrl).into(binding.ivMenuImg1)
        }
        if (response.information.menus[1].imageUrl.isNullOrEmpty()) {
            Glide.with(requireContext()).load(R.drawable.test_salad)
                .into(binding.ivMenuImg2)
        } else {
            Glide.with(requireContext()).load(response.information.menus[1].imageUrl).into(binding.ivMenuImg2)
        }
        binding.tvRestaurantName.text = response.information.restaurant.name
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
        binding.tvReviewCount.text = ("방문자 리뷰 ${response.information.totalCount}개")
        reViewList = arrayListOf()
        Log.d("onGetRestaurantReviewSuccess",response.toString())
        for (i: Int in 0 until response.information.reviews.size) {
            reViewList.add(response.information.reviews[i])
        }
//        로직상 오류
//        binding.tvReviewCount.text = "방문자 리뷰 ${response.information.reviews.size}개"
        dataRVAdapter  = RestaurantDetailReviewRVAdapter(reViewList)
        binding.rvRestaurantReviews.adapter = dataRVAdapter
        binding.rvRestaurantReviews.layoutManager = LinearLayoutManager(this.context)
        dismissLoadingDialog()
    }

    override fun onGetRestaurantReviewAddSuccess(response: RestaurantReviewResponse) {

        Log.d("onGetRestaurantReviewAddSuccess",response.toString())
        if(response.information.reviews.isNotEmpty()){
            for (i: Int in 0 until response.information.reviews.size) {
                reViewList.add(response.information.reviews[i])
            }
            binding.rvRestaurantReviews.adapter!!.notifyItemRangeChanged(0,reViewList.size)
        }else{
            checkLast = true
        }
        dismissLoadingDialog()
    }

    override fun onGetRestaurantReviewFailure(message: String) {
        Log.d("onGetRestaurantReviewFailure",message)
        dismissLoadingDialog()
    }

    override fun onPostScrapRestaurantSuccess(response: RestaurantScrapResponse) {
        Toast.makeText(requireContext(),response.information.message,Toast.LENGTH_SHORT).show()
        dismissLoadingDialog()
    }

    override fun onPostScrapRestaurantFailure(message: String) {
        Toast.makeText(requireContext(),"이미 스크랩이 되어 있습니다.",Toast.LENGTH_SHORT).show()
        Log.d("onPostScrapRestaurantFailure",message)
        dismissLoadingDialog()
    }

    override fun onDeleteScrapRestaurantSuccess(response: RestaurantScrapDeleteResponse) {
        Toast.makeText(requireContext(),response.information.message,Toast.LENGTH_SHORT).show()
        dismissLoadingDialog()
    }

    override fun onDeleteScrapRestaurantFailure(message: String) {
        Log.d("onDeleteScrapRestaurantFailure",message)
        dismissLoadingDialog()
    }

}