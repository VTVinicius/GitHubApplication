package com.example.domain.repository.github

import com.example.domain.model.github.SearchUserModel
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    fun searchUser(username: String) : Flow<SearchUserModel>
}