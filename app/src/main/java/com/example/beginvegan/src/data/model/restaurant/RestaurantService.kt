package com.example.beginvegan.src.data.model.restaurant

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.src.data.api.RestaurantRetrofitInterface
import com.example.beginvegan.config.ErrorResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantService(val restaurantInterface: RestaurantInterface){
    private val restaurantRetrofitInterface: RestaurantRetrofitInterface = ApplicationClass.sRetrofit.create(RestaurantRetrofitInterface::class.java)

    fun tryGetRestaurantDetail(restaurantId: Int){
        restaurantRetrofitInterface.getRestaurantsDetail(restaurantId).enqueue(object: Callback<RestaurantDetailResponse>{
            override fun onResponse(
                call: Call<RestaurantDetailResponse>,
                response: Response<RestaurantDetailResponse>
            ) {
                if(response.code()==200){
                    restaurantInterface.onGetRestaurantsDetailSuccess(response.body() as RestaurantDetailResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        restaurantInterface.onGetRestaurantsDetailFailure(errorResponse.message)
                    }catch(e:Exception){
                        restaurantInterface.onGetRestaurantsDetailFailure(e.message?:"통신 오류")
                    }

                }
            }
            override fun onFailure(call: Call<RestaurantDetailResponse>, t: Throwable) {
                restaurantInterface.onGetRestaurantsDetailFailure(t.message?:"통신 오류")
            }

        })
    }
}