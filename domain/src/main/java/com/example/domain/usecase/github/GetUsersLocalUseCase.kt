package com.example.domain.usecase.github

import com.example.domain.core.UseCase
import com.example.domain.exception.MissingParamsException
import com.example.domain.model.github.GitUserModel
import com.example.domain.model.github.SearchUserModel
import com.example.domain.repository.github.GithubRepository
import kotlinx.coroutines.CoroutineScope

class GetUsersLocalUseCase(
    scope: CoroutineScope,
    private val githubRepository: GithubRepository
): UseCase<GitUserModel, GetUsersLocalUseCase.Param>(scope) {
    override fun run(params: Param?) = when (params) {
        null -> throw MissingParamsException()
        else -> githubRepository.getGitUsers()
    }

    data class Param(
        val none: Unit?
    )

}