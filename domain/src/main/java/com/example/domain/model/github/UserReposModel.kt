package com.example.domain.model.github

import com.google.gson.annotations.SerializedName

data class UserReposModel(
    val id: Long = 0,
    val repoName: String? = "",
    val url: String? = "",
    val description: String? = "",
    val language: String? = ""
)
