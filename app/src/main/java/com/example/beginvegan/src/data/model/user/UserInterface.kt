package com.example.beginvegan.src.data.model.user

import com.example.beginvegan.src.data.model.restaurant.RestaurantDetailResponse

interface UserInterface {
    fun onGetUserSuccess(response: UserResponse)
    fun onGetUserFailure(message: String)

    fun onPostUserVeganTypeSuccess(response: VeganTypeResponse)
    fun onPostUserVeganTypeFailure(message: String)

    fun onGetUserBookmarksSuccess(response: List<RestaurantDetailResponse>)
    fun onGetUserBookmarksFailure(message: String)
}