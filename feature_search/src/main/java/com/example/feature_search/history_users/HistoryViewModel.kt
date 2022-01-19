package com.example.feature_search.history_users

import androidx.lifecycle.ViewModel
import com.example.base_feature.utils.extensions.*
import com.example.domain.model.github.GitUserModel
import com.example.domain.usecase.github.history.GetUsersLocalUseCase
import org.koin.core.KoinComponent

class HistoryViewModel : ViewModel(), KoinComponent {

    private val getUsersLocalUseCase: GetUsersLocalUseCase by useCase()

    private val _getUsersLocalViewState by viewState<List<GitUserModel>>()

    val getUsersLocalViewState = _getUsersLocalViewState.asLiveData()


    fun getUsersLocal() {
        getUsersLocalUseCase(
            onSuccess = {
                _getUsersLocalViewState.postSuccess(it)
            },
            onError = {
                _getUsersLocalViewState.postError(it)
            }
        )
    }
}

