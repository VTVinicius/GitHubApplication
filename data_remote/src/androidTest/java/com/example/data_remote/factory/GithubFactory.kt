package com.example.data_remote.factory

import com.example.data_remote.model.search_user.SearchUserResponse

object GithubFactory {

    val DUMMY_SEARCH_USER_RESPONSE = SearchUserResponse(
        id = 1,
        avatar_url = "url",
        login = "login",
        name = "name",
        email = "email",
        bio = "bio"
    )
}