package com.example.beginvegan.src.data.model.user

import com.example.beginvegan.config.BaseResponse
import com.example.beginvegan.src.data.model.Message
import com.google.gson.annotations.SerializedName

data class UserVeganResponse(
    @SerializedName("information") val information: Message
):BaseResponse()
