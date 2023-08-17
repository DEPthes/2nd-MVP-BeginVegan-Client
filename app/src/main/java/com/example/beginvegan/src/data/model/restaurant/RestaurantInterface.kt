package com.example.beginvegan.src.data.model.restaurant

interface RestaurantInterface {
    fun onGetRestaurantDetailSuccess(response:RestaurantDetailResponse)
    fun onGetRestaurantDetailFailure(message: String)

    fun onGetRestaurantReviewSuccess(response: RestaurantReviewResponse)
    fun onGetRestaurantReviewFailure(message: String)
}