package com.example.data_remote.datasource

import com.example.data.datasource.remote.GithubRemoteDataSource
import com.example.data_remote.mapper.github.GetUserFollowersMapper
import com.example.data_remote.mapper.github.SearchUserMapper
import com.example.data_remote.service.GithubWebService
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent

class GithubRemoteDataSourceImpl(
    private val webService: GithubWebService
) : GithubRemoteDataSource, KoinComponent {

    override fun searchUser(username: String) = flow {
        emit(
            SearchUserMapper.toDomain(
                webService.searchUser(username)
            )
        )
    }

    override fun getUserFollowers(username: String) = flow {
        emit(
            GetUserFollowersMapper.listToDomainFollowers(
                webService.getUserFollowers(username)
            )
        )
    }
    override fun getUserFollowing(username: String) = flow {
        emit(
            GetUserFollowersMapper.listToDomainFollowing(
                webService.getUserFollowing(username)
            )
        )
    }
}