package com.example.beginvegan.src.data.model.user

import com.example.beginvegan.src.data.model.restaurant.RestaurantDetailResponse

interface UserInterface {

    fun onPostUserVeganTypeSuccess(response: UserVeganResponse)
    fun onPostUserVeganTypeFailure(message: String)
}