package com.example.data_remote.service

import com.example.data_remote.model.search_user.SearchUserResponse
import com.example.data_remote.service.GithubWebService.GithubConstants.GITHUB_USERNAME
import com.example.data_remote.service.GithubWebService.GithubConstants.GITHUB_USER_BASE
import com.example.data_remote.utils.GITHUB_API_URL
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubWebService {

    @GET(GITHUB_USER_BASE)
    suspend fun searchUser(
        @Path(GITHUB_USERNAME) username: String
    ) : SearchUserResponse

    object GithubConstants {
        const val GITHUB_USERNAME = "vvv"
        const val GITHUB_USER_BASE = "$GITHUB_API_URL/users/{$GITHUB_USERNAME}"
    }
}