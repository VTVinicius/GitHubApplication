package com.example.data_remote.model.user_repos

import com.google.gson.annotations.SerializedName

data class UserReposResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val repoName: String,
    @SerializedName("html_url")
    val url: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("language")
    val language: String
)