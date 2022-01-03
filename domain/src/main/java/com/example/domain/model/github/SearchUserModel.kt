package com.example.domain.model.github

data class SearchUserModel(
    val id : Int = 0,
    val login: String? = "",
    val avatar_url: String? = "",
    val bio: String? = "",
    val email: String? = "",
    val name: String? = ""
)