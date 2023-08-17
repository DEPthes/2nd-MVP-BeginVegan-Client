package com.example.beginvegan.src.data.model.restaurant

interface RestaurantInterface {
    fun onGetRestaurantsDetailSuccess(response:RestaurantDetailResponse)
    fun onGetRestaurantsDetailFailure(message: String)
}