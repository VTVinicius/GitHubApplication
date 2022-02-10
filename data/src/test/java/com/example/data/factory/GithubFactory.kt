package com.example.data.factory

import com.example.domain.model.github.GitUserDataModel
import com.example.domain.model.github.GitUserModel
import com.example.domain.model.github.SearchUserModel

object GithubFactory {
    val DUMMY_SEARCH_USER_MODEL = SearchUserModel(
        id = 1,
        login = "login",
        avatar_url = "url",
        bio = "bio",
        email = "email",
        name = "name"
    )

    val DUMMY_LIST_GIT_USER_MODEL =
        listOf(GitUserModel(gitUserData = GitUserDataModel(user = DUMMY_SEARCH_USER_MODEL)))

    val DUMMY_GIT_USER_MODEL =
        GitUserModel(gitUserData = GitUserDataModel(user = DUMMY_SEARCH_USER_MODEL))
}