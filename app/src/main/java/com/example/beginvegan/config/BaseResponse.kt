package com.example.beginvegan.config

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("check") val check: Boolean = false
)