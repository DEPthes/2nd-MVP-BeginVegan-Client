package com.example.beginvegan.config

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
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
    @SerializedName("errors")
    val errors: List<CustomFieldError>
)
data class CustomFieldError(
    @SerializedName("field")
    val field: String,
    @SerializedName("value")
    // String 타입 아님 뭔지 알면 등록 하십셔
    val value: String,
    @SerializedName("reason")
    val reason: String
)
