package com.example.data.datasource.remote

import com.example.domain.model.github.SearchUserModel
import com.example.domain.model.github.UserReposModel
import kotlinx.coroutines.flow.Flow

interface GithubRemoteDataSource {
    fun searchUser(username: String): Flow<SearchUserModel>
    fun getUserFollowers(username: String): Flow<List<SearchUserModel>>
    fun getUserFollowing(username: String): Flow<List<SearchUserModel>>
    fun getUserRepos(username: String): Flow<List<UserReposModel>>
}