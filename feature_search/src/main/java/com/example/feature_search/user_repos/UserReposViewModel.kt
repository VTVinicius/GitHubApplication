package com.example.feature_search.user_repos

import androidx.lifecycle.ViewModel
import com.example.base_feature.utils.extensions.*
import com.example.domain.model.github.UserReposModel
import com.example.domain.usecase.github.user_repos.GetUserReposUseCase
import org.koin.core.KoinComponent

class UserReposViewModel : ViewModel(), KoinComponent {

    private val getUserReposUseCase: GetUserReposUseCase by useCase()

    private val _getUserReposViewState by viewState<List<UserReposModel>>()

    val getUserReposViewState = _getUserReposViewState.asLiveData()


    fun getRepos(username: String) {
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

}