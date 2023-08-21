package com.example.beginvegan.src.data.model.restaurant

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.RestaurantRetrofitInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantScrapDeleteService(val restaurantScrapDeleteInterface: RestaurantScrapDeleteInterface) {
    private val restaurantRetrofitInterface: RestaurantRetrofitInterface = ApplicationClass.sRetrofit.create(RestaurantRetrofitInterface::class.java)

    fun tryDeleteScrapRestaurant(restaurantId: Int){
        restaurantRetrofitInterface.deleteScrapRestaurant(ApplicationClass.xAccessToken,restaurantId).enqueue(object:
            Callback<RestaurantScrapDeleteResponse> {
            override fun onResponse(
                call: Call<RestaurantScrapDeleteResponse>,
                response: Response<RestaurantScrapDeleteResponse>
            ) {
                if(response.code()==200){
                    restaurantScrapDeleteInterface.onDeleteScrapRestaurantSuccess(response.body() as RestaurantScrapDeleteResponse)
                }else{
                    try{
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                        restaurantScrapDeleteInterface.onDeleteScrapRestaurantFailure(errorResponse.message)
                    }catch(e:Exception){
                        restaurantScrapDeleteInterface.onDeleteScrapRestaurantFailure(e.message?:"통신 오류")
                    }

                }
            }

            override fun onFailure(call: Call<RestaurantScrapDeleteResponse>, t: Throwable) {
                restaurantScrapDeleteInterface.onDeleteScrapRestaurantFailure(t.message?:"통신 오류")
            }

        })
    }
}