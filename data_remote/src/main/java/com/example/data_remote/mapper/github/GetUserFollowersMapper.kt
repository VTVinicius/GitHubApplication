package com.example.data_remote.mapper.github

import com.example.data_remote.mapper.DataRemoteMapper
import com.example.data_remote.model.search_user.SearchUserResponse
import com.example.domain.model.github.SearchUserModel

object GetUserFollowersMapper : DataRemoteMapper<SearchUserResponse, SearchUserModel>() {
    override fun toDomain(data: SearchUserResponse) = SearchUserModel(
        login = data.login,
        avatar_url = data.avatar_url,
        bio = data.bio,
        email = data.email,
        id = data.id,
        name = data.name
    )

    fun listToDomainFollowers(list: List<SearchUserResponse>?): List< SearchUserModel> {
        val listResponse = mutableListOf<SearchUserModel>()
        list?.forEach {
            listResponse.add(
                toDomain(it)
            )
        }
        return listResponse.toList()
    }
    fun listToDomainFollowing(list: List<SearchUserResponse>?): List< SearchUserModel> {
        val listResponse = mutableListOf<SearchUserModel>()
        list?.forEach {
            listResponse.add(
                toDomain(it)
            )
        }
        return listResponse.toList()
    }
}
