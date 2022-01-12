package com.example.data_local.datasource

import com.example.data.datasource.local.GithubLocalDataSource
import com.example.data_local.database.GitUserDao
import com.example.data_local.mapper.GitUserDataMapper.toDao
import com.example.data_local.mapper.GitUserDataMapper.toDomain
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

    override fun getSingleUserData(userID: Long): Flow<GitUserModel>  = flow{
        gitUserDao.getSingleUser(userID).collect {
            emit(it.toDomain())

        }
    }

}