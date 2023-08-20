package com.example.beginvegan.src.data.model.auth

interface AuthSignInterface {
    fun onPostAuthSignInSuccess(response: AuthSignResponse)
    fun onPostAuthSignInFailed(message: String)
    fun onPostAuthSignUpSuccess(response: AuthSignResponse)
    fun onPostAuthSignUpFailed(message: String)
}