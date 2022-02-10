package com.example.domain.usecase.github.search_user

import com.example.domain.core.UseCase
import com.example.domain.exception.MissingParamsException
import com.example.domain.model.github.GitUserModel
import com.example.domain.repository.github.GithubRepository
import kotlinx.coroutines.CoroutineScope

class SaveGitUserUseCase(
    scope: CoroutineScope,
    private val githubRepository: GithubRepository
) : UseCase<Unit, SaveGitUserUseCase.Params>(scope) {

    override fun run(params: Params?) = when (params) {
        null -> throw MissingParamsException()
        else -> githubRepository.saveGitUserData(params.user)
    }

    data class Params(
        val user: GitUserModel
    )
}