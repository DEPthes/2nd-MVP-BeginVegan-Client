package com.example.beginvegan.src.data.model.restaurant

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.RestaurantRetrofitInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantFindService(val restaurantFindInterface: RestaurantFindInterface){
    private val restaurantRetrofitInterface: RestaurantRetrofitInterface = ApplicationClass.sRetrofit.create(RestaurantRetrofitInterface::class.java)

    fun tryPostFindRestaurant(coordinate: Coordinate){
        restaurantRetrofitInterface.postFindRestaurant(ApplicationClass.xAccessToken,coordinate).enqueue(object :
            Callback<RestaurantFindResponse>{
            override fun onResponse(
                call: Call<RestaurantFindResponse>,
                response: Response<RestaurantFindResponse>
            ) {
                if(response.code()==200){
                    restaurantFindInterface.onPostFindRestaurantSuccess(response.body() as RestaurantFindResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        restaurantFindInterface.onPostFindRestaurantFailure(errorResponse.message)
                    }catch(e:Exception){
                        restaurantFindInterface.onPostFindRestaurantFailure(e.message?:"통신 오류")
                    }

                }
            }

            override fun onFailure(call: Call<RestaurantFindResponse>, t: Throwable) {
                restaurantFindInterface.onPostFindRestaurantFailure(t.message?:"통신 오류")
            }

        })
    }
}