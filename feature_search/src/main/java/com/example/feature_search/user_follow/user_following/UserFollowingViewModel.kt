package com.example.feature_search.user_follow.user_following

import androidx.lifecycle.ViewModel
import com.example.base_feature.utils.extensions.*
import com.example.domain.model.github.SearchUserModel
import com.example.domain.usecase.github.user_profile.GetUserFollowingUseCase
import org.koin.core.KoinComponent

class UserFollowingViewModel : ViewModel(), KoinComponent {

    private val getUserFollowingUseCase: GetUserFollowingUseCase by useCase()

    private val _getUserFollowingViewState by viewState<List<SearchUserModel>>()

    val getUserFollowingViewState = _getUserFollowingViewState.asLiveData()

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

}