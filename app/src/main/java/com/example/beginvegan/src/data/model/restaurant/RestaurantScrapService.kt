package com.example.beginvegan.src.data.model.restaurant

import com.example.beginvegan.config.ApplicationClass
import com.example.beginvegan.config.ErrorResponse
import com.example.beginvegan.src.data.api.RestaurantRetrofitInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantScrapService(val restaurantScrapInterface: RestaurantScrapInterface) {
    private val restaurantRetrofitInterface: RestaurantRetrofitInterface =
        ApplicationClass.sRetrofit.create(RestaurantRetrofitInterface::class.java)

    fun tryPostScrapRestaurant(restaurantId: Int) {
        restaurantRetrofitInterface.postScrapRestaurant(ApplicationClass.xAccessToken, restaurantId)
            .enqueue(object :
                Callback<RestaurantScrapResponse> {
                override fun onResponse(
                    call: Call<RestaurantScrapResponse>,
                    response: Response<RestaurantScrapResponse>
                ) {
                    if (response.code() == 200) {
                        restaurantScrapInterface.onPostScrapRestaurantSuccess(response.body() as RestaurantScrapResponse)
                    } else {
                        try {
                            val gson = Gson()
                            val errorResponse =
                                gson.fromJson(
                                    response.errorBody()?.string(),
                                    ErrorResponse::class.java
                                )
                            restaurantScrapInterface.onPostScrapRestaurantFailure(errorResponse.message)
                        } catch (e: Exception) {
                            restaurantScrapInterface.onPostScrapRestaurantFailure(
                                e.message ?: "통신 오류"
                            )
                        }

                    }
                }

                override fun onFailure(call: Call<RestaurantScrapResponse>, t: Throwable) {
                    restaurantScrapInterface.onPostScrapRestaurantFailure(t.message ?: "통신 오류")
                }

            })
    }
}