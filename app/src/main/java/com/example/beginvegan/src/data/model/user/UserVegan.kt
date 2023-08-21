package com.example.beginvegan.src.data.model.user

import com.example.beginvegan.util.VeganType
import com.google.gson.annotations.SerializedName

data class UserVegan (
    @SerializedName("veganType") val veganType: String
)