package com.example.beginvegan.src.data.model.restaurant

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.src.data.api.RestaurantRetrofitInterface
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.util.Constants
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantService(val restaurantInterface: RestaurantInterface){
    private val restaurantRetrofitInterface: RestaurantRetrofitInterface = ApplicationClass.sRetrofit.create(RestaurantRetrofitInterface::class.java)
    private val accessToken = ("Bearer "+(ApplicationClass.xAccessToken))

    // 식당/카페 상세 정보(메뉴까지) 조회
    fun tryGetRestaurantDetail(restaurantId: Int){
        restaurantRetrofitInterface.getRestaurantsDetail(accessToken,restaurantId).enqueue(object: Callback<RestaurantDetailResponse>{
            override fun onResponse(
                call: Call<RestaurantDetailResponse>,
                response: Response<RestaurantDetailResponse>
            ) {
                if(response.code()==200){
                    restaurantInterface.onGetRestaurantDetailSuccess(response.body() as RestaurantDetailResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        restaurantInterface.onGetRestaurantDetailFailure(errorResponse.message)
                    }catch(e:Exception){
                        restaurantInterface.onGetRestaurantDetailFailure(e.message?:"통신 오류")
                    }

                }
            }
            override fun onFailure(call: Call<RestaurantDetailResponse>, t: Throwable) {
                restaurantInterface.onGetRestaurantDetailFailure(t.message?:"통신 오류")
            }

        })
    }

    // 식당/카페 리뷰 조회
    fun tryGetRestaurantReview(restaurantId: Int){
        restaurantRetrofitInterface.getRestaurantReview(accessToken,restaurantId).enqueue(object: Callback<RestaurantReviewResponse>{
            override fun onResponse(
                call: Call<RestaurantReviewResponse>,
                response: Response<RestaurantReviewResponse>
            ) {
                if(response.code()==200){
                    restaurantInterface.onGetRestaurantReviewSuccess(response.body() as RestaurantReviewResponse)
                }
                else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        restaurantInterface.onGetRestaurantReviewFailure(errorResponse.message)
                    }catch(e:Exception){
                        restaurantInterface.onGetRestaurantReviewFailure(e.message?:"통신 오류")
                    }
                }
            }

            override fun onFailure(call: Call<RestaurantReviewResponse>, t: Throwable) {
                restaurantInterface.onGetRestaurantReviewFailure(t.message?:"통신 오류")
            }

        })
    }


}