package com.example.feature_search.search_user
import androidx.lifecycle.ViewModel
import com.example.base_feature.utils.extensions.*
import com.example.domain.model.github.GitUserDataModel
import com.example.domain.model.github.GitUserModel
import com.example.domain.model.github.SearchUserModel
import com.example.domain.usecase.github.search_user.SaveGitUserUseCase
import com.example.domain.usecase.github.search_user.SearchUserUseCase
import org.koin.core.KoinComponent

class SearchUserViewModel : ViewModel(), KoinComponent {

    private val searchUserUseCase: SearchUserUseCase by useCase()
    private val saveGitUserUseCase: SaveGitUserUseCase by useCase()

    private val _searchUserViewState by viewState<SearchUserModel>()
    private val _saveGitUserViewSate by viewState<SearchUserModel>()

    val searchUserViewState = _searchUserViewState.asLiveData()
    val saveGitUserViewSate = _saveGitUserViewSate.asLiveData()


    fun searchUser(username: String) {
        searchUserUseCase(
            params = SearchUserUseCase.Params(username),
            onSuccess = {
                _searchUserViewState.postSuccess(it)
                saveUserLocal(it)
            },
            onError = {
                _searchUserViewState.postError(it)
            }
        )
    }

     fun saveUserLocal(user: SearchUserModel) {
        saveGitUserUseCase(
            params = SaveGitUserUseCase.Params(
                GitUserModel(
                     gitUserData = GitUserDataModel(user)
                )
            ),
            onSuccess = {
                _saveGitUserViewSate.postSuccess(user)
            },
            onError = {
                _saveGitUserViewSate.postError(it)
            }
        )
    }

    fun resetViewState(){
        _searchUserViewState.postNeutral()
        _saveGitUserViewSate.postNeutral()
    }

}

