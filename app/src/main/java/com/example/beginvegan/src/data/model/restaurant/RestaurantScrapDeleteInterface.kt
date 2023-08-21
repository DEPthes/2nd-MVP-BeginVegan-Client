package com.example.beginvegan.src.data.model.restaurant

interface RestaurantScrapDeleteInterface {
    fun onDeleteScrapRestaurantSuccess(response: RestaurantScrapDeleteResponse)
    fun onDeleteScrapRestaurantFailure(message: String)
}