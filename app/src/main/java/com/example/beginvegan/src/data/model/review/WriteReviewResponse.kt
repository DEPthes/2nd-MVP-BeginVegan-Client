package com.example.beginvegan.src.data.model.review

import com.example.beginvegan.config.BaseResponse
import com.example.beginvegan.src.data.model.Message
import com.google.gson.annotations.SerializedName

data class WriteReviewResponse(
    @SerializedName("information") val information: Message
):BaseResponse()
