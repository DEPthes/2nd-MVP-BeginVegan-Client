package com.example.beginvegan.src.data.model.restaurant

interface RestaurantFindInterface {
    fun onPostFindRestaurantSuccess(response: RestaurantFindResponse)
    fun onPostFindRestaurantFailure(message: String)
}