package com.example.data_local.database.mapper

import com.example.data_local.database.model.GitUserDataLocal
import com.example.data_local.database.model.GitUserLocal
import com.example.domain.model.github.GitUserDataModel
import com.example.domain.model.github.GitUserModel
import com.example.domain.model.github.SearchUserModel

object GitUserDataMapper {

    fun GitUserModel.toDao() = GitUserLocal(
        gitUserData = GitUserDataLocal(
            avatar_url = gitUserData.user.avatar_url,
            bio = gitUserData.user.bio,
            email = gitUserData.user.email,
            id = gitUserData.user.id,
            login = gitUserData.user.login,
            name = gitUserData.user.name
        )
    )

    fun GitUserLocal.toDomain() = GitUserModel(
        gitUserData = GitUserDataModel(
            user = SearchUserModel()
        )
    )


}