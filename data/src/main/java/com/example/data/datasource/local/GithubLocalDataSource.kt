package com.example.data.datasource.local

import com.example.domain.model.github.GitUserModel
import kotlinx.coroutines.flow.Flow

interface GithubLocalDataSource {

    fun saveGitUserData(gitUserModel: GitUserModel)
    fun getGitUserData(): Flow<List<GitUserModel>>

}