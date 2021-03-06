package com.example.domain.factory

import com.example.domain.model.github.GitUserDataModel
import com.example.domain.model.github.GitUserModel
import com.example.domain.model.github.SearchUserModel

object GithubFactory {

    private val DUMMY_SEARCH_USER_MODEL = SearchUserModel(
        id = 1,
        avatar_url = "url",
        login = "login",
        name = "name",
        email = "email",
        bio = "bio"
    )


    val DUMMY_GIT_USER_MODEL = GitUserModel(gitUserData = GitUserDataModel(DUMMY_SEARCH_USER_MODEL))


    const val DUMMY_USERID_VALUE: Long = 10
}
