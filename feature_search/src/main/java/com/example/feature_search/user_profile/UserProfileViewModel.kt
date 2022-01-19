package com.example.feature_search.user_profile

import androidx.lifecycle.ViewModel
import com.example.base_feature.utils.extensions.*
import com.example.domain.model.github.GitUserModel
import com.example.domain.usecase.github.user_profile.GetSingleUserLocalUseCase
import org.koin.core.KoinComponent

class UserProfileViewModel : ViewModel(), KoinComponent {

    private val getSingleUserLocalUseCase: GetSingleUserLocalUseCase by useCase()

    private val _getSingleUserViewState by viewState<GitUserModel>()

    val getSingleUserViewState = _getSingleUserViewState.asLiveData()

    private var userID: Long = 36412487


    fun getSingleUser(id: Long?) {
        getSingleUserLocalUseCase(
            params = GetSingleUserLocalUseCase.Params(id ?: userID),
            onSuccess = {
                _getSingleUserViewState.postSuccess(it)
            },
            onError = {
                _getSingleUserViewState.postError(it)
            }
        )
    }

}

