package com.example.domain.usecase.github

import com.example.domain.core.UseCase
import com.example.domain.exception.MissingParamsException
import com.example.domain.model.github.GitUserModel
import com.example.domain.model.github.SearchUserModel
import com.example.domain.repository.github.GithubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetUsersLocalUseCase(
    scope: CoroutineScope,
    private val githubRepository: GithubRepository
) : UseCase<List<GitUserModel>, Unit>(scope) {
    override fun run(params: Unit?): Flow<List<GitUserModel>> =
        githubRepository.getGitUsers()


}