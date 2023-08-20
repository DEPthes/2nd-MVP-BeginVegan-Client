package com.example.beginvegan.src.data.model.user

interface UserCheckInterface {
    fun onGetUserSuccess(response: UserResponse)
    fun onGetUserFailure(message: String)
}