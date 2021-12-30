package com.example.data_remote.model.search_user

import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("login")
    val login: String?,
    @SerializedName("avatar_url")
    val avatar_url: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("email")
    val email: String?
)
