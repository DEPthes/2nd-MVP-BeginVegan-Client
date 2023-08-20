package com.example.beginvegan.src.data.model.user

interface UserModifyNameInterface {
    fun onPostUserModifyNameSuccess(response: UserModifyNameResponse)
    fun onPostUserModifyNameFailure(message: String)

}