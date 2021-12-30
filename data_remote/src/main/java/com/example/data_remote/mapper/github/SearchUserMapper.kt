package com.example.data_remote.mapper.github

import com.example.data_remote.mapper.user.DataRemoteMapper
import com.example.data_remote.model.search_user.SearchUserResponse
import com.example.domain.model.github.SearchUserModel

object SearchUserMapper : DataRemoteMapper<SearchUserResponse, SearchUserModel>() {
    override fun toDomain(data: SearchUserResponse) = SearchUserModel(
        login = data.login,
        avatar_url = data.avatar_url,
        bio = data.bio,
        email = data.email
    )
}