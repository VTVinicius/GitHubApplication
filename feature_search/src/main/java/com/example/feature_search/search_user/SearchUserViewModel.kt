package com.example.feature_search.search_user
import androidx.lifecycle.ViewModel
import com.example.base_feature.utils.extensions.*
import com.example.domain.model.github.SearchUserModel
import com.example.domain.usecase.github.SearchUserUseCase
import org.koin.core.KoinComponent

class SearchUserViewModel : ViewModel(), KoinComponent {

    private val searchUserUseCase: SearchUserUseCase by useCase()

    private val _searchUserViewState by viewState<SearchUserModel>()

    val searchUserViewState = _searchUserViewState.asLiveData()


    fun searchUser(username: String) {
        searchUserUseCase(
            params = SearchUserUseCase.Params(username),
            onSuccess = {
                _searchUserViewState.postSuccess(it)
            },
            onError = {
                _searchUserViewState.postError(it)
            }
        )
    }

    fun resetViewState(){
        _searchUserViewState.postNeutral()
    }

}

