package com.example.data_local.database.datasource

import com.example.data.datasource.local.GithubLocalDataSource
import com.example.data_local.database.GitUserDao
import com.example.data_local.database.mapper.GitUserDataMapper.toDao
import com.example.data_local.database.mapper.GitUserDataMapper.toDomain
import com.example.domain.model.github.GitUserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GithubLocalDataSourceImpl(
    private val gitUserDao: GitUserDao
) : GithubLocalDataSource {

    override fun saveGitUserData(gitUserModel: GitUserModel) {
        gitUserDao.addUser(gitUser = gitUserModel.toDao())
    }

    override fun getGitUserData(): Flow<List<GitUserModel>> = flow {
        gitUserDao.getAll().collect {
            val list = mutableListOf<GitUserModel>()
            it.forEach { item ->
                list.add(item.toDomain())
            }
            emit(list)
        }
    }
}