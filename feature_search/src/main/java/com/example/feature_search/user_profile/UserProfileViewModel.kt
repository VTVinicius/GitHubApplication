package com.example.feature_search.user_profile

import androidx.lifecycle.ViewModel
import com.example.base_feature.utils.extensions.*
import com.example.domain.model.github.GitUserModel
import com.example.domain.model.github.SearchUserModel
import com.example.domain.model.github.UserReposModel
import com.example.domain.usecase.github.user_profile.GetSingleUserLocalUseCase
import com.example.domain.usecase.github.user_profile.GetUserFollowersUseCase
import com.example.domain.usecase.github.user_profile.GetUserFollowingUseCase
import com.example.domain.usecase.github.user_repos.GetUserReposUseCase
import org.koin.core.KoinComponent

class UserProfileViewModel : ViewModel(), KoinComponent {

    private val getSingleUserLocalUseCase: GetSingleUserLocalUseCase by useCase()
    private val getUserFollowersUseCase: GetUserFollowersUseCase by useCase()
    private val getUserFollowingUseCase: GetUserFollowingUseCase by useCase()
    private val getUserReposUseCase: GetUserReposUseCase by useCase()

    private val _getSingleUserViewState by viewState<GitUserModel>()
    private val _getUserFollowersViewState by viewState<List<SearchUserModel>>()
    private val _getUserFollowingViewState by viewState<List<SearchUserModel>>()
    private val _getUserReposViewState by viewState<List<UserReposModel>>()

    val getSingleUserViewState = _getSingleUserViewState.asLiveData()
    val getUserFollowersViewState = _getUserFollowersViewState.asLiveData()
    val getUserFollowingViewState = _getUserFollowingViewState.asLiveData()
    val getUserReposViewState = _getUserReposViewState.asLiveData()


    fun getSingleUser(id: Long?) {
        getSingleUserLocalUseCase(
            params = GetSingleUserLocalUseCase.Params(id!!),
            onSuccess = {
                _getSingleUserViewState.postSuccess(it)
            },
            onError = {
                _getSingleUserViewState.postError(it)
            }
        )
    }

    fun getUserFollowers(username: String) {
        getUserFollowersUseCase(
            params = GetUserFollowersUseCase.Params(username),
            onSuccess = {
                _getUserFollowersViewState.postSuccess(it)
            },
            onError = {
                _getUserFollowersViewState.postError(it)
            }
        )
    }

    fun getUserFollowing(username: String) {
        getUserFollowingUseCase(
            params = GetUserFollowingUseCase.Params(username),
            onSuccess = {
                _getUserFollowingViewState.postSuccess(it)
            },
            onError = {
                _getUserFollowingViewState.postError(it)
            }
        )
    }

    fun getNumberRepos(username: String) {
        getUserReposUseCase(
            params = GetUserReposUseCase.Params(username),
            onSuccess = {
                _getUserReposViewState.postSuccess(it)
            },
            onError = {
                _getUserReposViewState.postError(it)
            }
        )
    }

    fun getWebUser(username: String){

    }

}



