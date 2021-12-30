package com.example.data.repository

import com.example.data.datasource.remote.GithubRemoteDataSource
import com.example.domain.repository.github.GithubRepository

class GithubRepositoryImpl(
    private val githubRemoteDataSource: GithubRemoteDataSource
) : GithubRepository {
    override fun searchUser(username: String) = githubRemoteDataSource.searchUser(username)
}