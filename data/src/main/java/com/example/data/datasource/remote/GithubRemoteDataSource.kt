package com.example.data.datasource.remote

import com.example.domain.model.github.GithubModel
import kotlinx.coroutines.flow.Flow

interface GithubRemoteDataSource {
    fun searchUser(username: String) : Flow<GithubModel>
}