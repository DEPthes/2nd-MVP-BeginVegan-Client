package com.example.beginvegan.src.data.model.restaurant

interface RestaurantScrapInterface {
    fun onPostScrapRestaurantSuccess(response: RestaurantScrapResponse)
    fun onPostScrapRestaurantFailure(message: String)
}