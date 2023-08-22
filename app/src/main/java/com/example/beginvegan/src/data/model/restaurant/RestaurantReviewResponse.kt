package com.example.beginvegan.src.data.model.restaurant

import com.example.beginvegan.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class RestaurantReviewResponse(
    @SerializedName("information") val information: RestaurantReview
) : BaseResponse()

data class RestaurantReview(
    @SerializedName("reviews") val reviews: List<ReviewDetail>,
    @SerializedName("totalCount") val totalCount: Int
)

data class ReviewDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("content") val content: String,
    @SerializedName("date") val date: String,
    @SerializedName("user") val user: ReviewUserDetail,
)

data class ReviewUserDetail(
    @SerializedName("createdDate") val createdDate: String,
    @SerializedName("modifiedDate") val modifiedDate: String,
    @SerializedName("status") val status: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("emailVerified") val emailVerified: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("marketingConsent") val marketingConsent: Boolean,
    @SerializedName("veganType") val veganType: String,
    @SerializedName("provider") val provider: String,
    @SerializedName("role") val role: String,
    @SerializedName("providerId") val providerId: String
)
