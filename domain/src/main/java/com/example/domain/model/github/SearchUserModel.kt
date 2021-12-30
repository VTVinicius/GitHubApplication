package com.example.domain.model.github

import com.google.gson.annotations.SerializedName

data class SearchUserModel(
    val login: String?,
    val avatar_url: String?,
    val bio: String?,
    val email: String?
)