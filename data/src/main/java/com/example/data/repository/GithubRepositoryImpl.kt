package com.example.data.repository

import com.example.data.datasource.local.GithubLocalDataSource
import com.example.data.datasource.remote.GithubRemoteDataSource
import com.example.domain.model.github.GitUserModel
import com.example.domain.repository.github.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GithubRepositoryImpl(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val githubLocalDataSource: GithubLocalDataSource
) : GithubRepository {

    override fun searchUser(username: String) = githubRemoteDataSource.searchUser(username)


    override fun getGitUsers(): Flow<List<GitUserModel>> = githubLocalDataSource.getGitUserData()

    override fun saveGitUserData(gitUserModel: GitUserModel) =
        flow { emit(githubLocalDataSource.saveGitUserData(gitUserModel)) }

    override fun getSingleUser(userID: Long) = githubLocalDataSource.getSingleUserData(userID)
    }
