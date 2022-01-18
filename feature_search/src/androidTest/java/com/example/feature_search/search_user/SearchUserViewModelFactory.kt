package com.example.feature_search.search_user

import com.example.domain.model.github.GitUserDataModel
import com.example.domain.model.github.GitUserModel
import com.example.domain.model.github.SearchUserModel

object SearchUserViewModelFactory {
    val DUMMY_SEARCH_USER_MODEL = SearchUserModel(
        1,
        "mock",
        "mock",
        "mock",
        "mock",
        "mock",
    )

    val DUMMY_GIT_USER_DATA_MODEL = GitUserDataModel(
        DUMMY_SEARCH_USER_MODEL
    )

    val DUMMY_GIT_USER_MODEL = GitUserModel(
        DUMMY_GIT_USER_DATA_MODEL
    )
}