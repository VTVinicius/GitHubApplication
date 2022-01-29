package com.example.data_remote.service

import com.example.data_remote.model.search_user.SearchUserResponse
import com.example.data_remote.service.GithubWebService.GithubConstants.GITHUB_FOLLOWERS
import com.example.data_remote.service.GithubWebService.GithubConstants.GITHUB_FOLLOWING
import com.example.data_remote.service.GithubWebService.GithubConstants.GITHUB_REPOS
import com.example.data_remote.service.GithubWebService.GithubConstants.GITHUB_USERNAME
import com.example.data_remote.service.GithubWebService.GithubConstants.GITHUB_USER_BASE
import com.example.data_remote.utils.GITHUB_API_URL
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubWebService {

    @GET(GITHUB_USER_BASE)
    suspend fun searchUser(
        @Path(GITHUB_USERNAME) username: String
    ): SearchUserResponse

    @GET(GITHUB_FOLLOWERS)
    suspend fun getUserFollowers(
        @Path(GITHUB_USERNAME) username: String
    ): List<SearchUserResponse>

    @GET(GITHUB_FOLLOWING)
    suspend fun getUserFollowing(
        @Path(GITHUB_USERNAME) username: String
    ): List<SearchUserResponse>

    @GET(GITHUB_REPOS)
    suspend fun getUserRepos(
        @Path(GITHUB_USERNAME) username: String
    ): List<SearchUserResponse>

    object GithubConstants {
        const val GITHUB_USERNAME = "mock"
        const val GITHUB_USER_BASE = "$GITHUB_API_URL/users/{$GITHUB_USERNAME}"
        const val GITHUB_FOLLOWERS = "$GITHUB_USER_BASE/followers"
        const val GITHUB_FOLLOWING = "$GITHUB_USER_BASE/following"
        const val GITHUB_REPOS = "$GITHUB_USER_BASE/repos"
    }
}