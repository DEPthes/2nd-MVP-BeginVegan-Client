package com.example.beginvegan.src.data.model.user

import com.example.beginvegan.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class UserModifyNameResponse(
    @SerializedName("information") val information: ModifyName
): BaseResponse()

data class ModifyName(
    @SerializedName("message") val message:String
)