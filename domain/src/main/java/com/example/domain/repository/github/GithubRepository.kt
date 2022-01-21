package com.example.domain.repository.github

import com.example.domain.model.github.GitUserModel
import com.example.domain.model.github.SearchUserModel
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    fun searchUser(username: String): Flow<SearchUserModel>
    fun getUserFollowers(username: String): Flow<List<SearchUserModel>>
    fun getUserFollowing(username: String): Flow<List<SearchUserModel>>

    fun getGitUsers(): Flow<List<GitUserModel>>
    fun saveGitUserData(gitUserModel: GitUserModel): Flow<Unit>
    fun getSingleUser(userID: Long): Flow<GitUserModel>

}