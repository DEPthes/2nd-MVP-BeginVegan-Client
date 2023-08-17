package com.example.beginvegan.config

import com.google.gson.annotations.SerializedName

open class ErrorResponse(
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("class")
    val errorResponse: String,
)
