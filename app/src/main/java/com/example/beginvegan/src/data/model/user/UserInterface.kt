package com.example.beginvegan.src.data.model.user

interface UserInterface {
    fun onGetUserSuccess(response: UserResponse)
    fun onGetUserFailure(message: String)

    fun onPostUserVeganTypeSuccess(response: VeganTypeResponse)
    fun onPostUserVeganTypeFailure(message: String)
}