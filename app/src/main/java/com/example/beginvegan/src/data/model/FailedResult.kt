package com.example.beginvegan.src.data.model

import com.google.gson.annotations.SerializedName

open class FailedResult(
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("class")
    val ex: String,
)
