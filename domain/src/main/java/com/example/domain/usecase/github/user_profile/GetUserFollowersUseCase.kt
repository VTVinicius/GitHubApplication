package com.example.domain.usecase.github.user_profile

import com.example.domain.core.UseCase
import com.example.domain.exception.EmptyFieldException
import com.example.domain.exception.MissingParamsException
import com.example.domain.model.github.SearchUserModel
import com.example.domain.repository.github.GithubRepository
import kotlinx.coroutines.CoroutineScope

class GetUserFollowersUseCase(
    scope: CoroutineScope,
    private val githubRepository: GithubRepository
) : UseCase<List<SearchUserModel>, GetUserFollowersUseCase.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw MissingParamsException()
        params.username.isBlank() -> throw EmptyFieldException()
        else -> githubRepository.getUserFollowers(params.username)
    }

    data class Params(
        val username: String
    )
}