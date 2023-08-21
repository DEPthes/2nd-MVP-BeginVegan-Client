package com.example.beginvegan.src.data.model.user

interface UserScrapInterface {
    fun onGetUserBookmarksSuccess(response: UserScrapResponse)
    fun onGetUserBookmarksAddSuccess(response: UserScrapResponse)
    fun onGetUserBookmarksFailure(message: String)
}