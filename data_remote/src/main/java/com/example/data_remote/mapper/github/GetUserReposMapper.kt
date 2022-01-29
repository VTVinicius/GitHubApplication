package com.example.data_remote.mapper.github

import com.example.data_remote.mapper.DataRemoteMapper
import com.example.data_remote.model.user_repos.UserReposResponse
import com.example.domain.model.github.UserReposModel

object GetUserReposMapper : DataRemoteMapper<UserReposResponse, UserReposModel>() {

    fun listToDomainRepos(list: List<UserReposResponse>?): List<UserReposModel> {
        val listResponse = mutableListOf<UserReposModel>()
        list?.forEach {
            listResponse.add(
                GetUserReposMapper.toDomain(it)
            )
        }
        return listResponse.toList()
    }

    override fun toDomain(data: UserReposResponse) = UserReposModel(
        id = 0,
        repoName = "",
        url = "",
        description = "",
        language = ""
    )

}
