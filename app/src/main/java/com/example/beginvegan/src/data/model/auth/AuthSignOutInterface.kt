package com.example.beginvegan.src.data.model.auth

interface AuthSignOutInterface {
    fun onPostAuthSignOutSuccess(response: AuthSignOutResponse)
    fun onPostAuthSignOutFailure(message: String)
}