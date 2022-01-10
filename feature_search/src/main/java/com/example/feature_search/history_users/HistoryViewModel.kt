package com.example.feature_search.history_users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.base_feature.utils.extensions.*
import com.example.domain.model.github.GitUserModel
import com.example.domain.model.github.SearchUserModel
import com.example.domain.usecase.github.GetUsersLocalUseCase
import com.example.domain.usecase.github.SaveGitUserUseCase
import com.example.domain.usecase.github.SearchUserUseCase
import org.koin.core.KoinComponent

class HistoryViewModel : ViewModel(), KoinComponent {

    private val getUsersLocalUseCase: GetUsersLocalUseCase by useCase()

    private val _getUsersLocalViewState by viewState<GitUserModel>()

    val getUsersLocalViewState = _getUsersLocalViewState.asLiveData()


    fun getUsersLocal() {
        getUsersLocalUseCase(
            params = GetUsersLocalUseCase.Param(Unit),
            onSuccess = {
                _getUsersLocalViewState.postSuccess(it)
            },
            onError = {
                _getUsersLocalViewState.postError(it)
            }
        )
    }

}

