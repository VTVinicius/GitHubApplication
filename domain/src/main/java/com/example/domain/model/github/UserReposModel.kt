package com.example.domain.model.github

data class UserReposModel(
    val id: Long = 0,
    val repoName: String? = "",
    val url: String? = "",
    val description: String? = "",
    val language: String? = ""
)
