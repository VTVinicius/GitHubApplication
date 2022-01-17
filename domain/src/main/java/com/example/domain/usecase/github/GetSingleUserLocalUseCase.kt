package com.example.domain.usecase.github

import com.example.domain.core.UseCase
import com.example.domain.model.github.GitUserModel
import com.example.domain.repository.github.GithubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetSingleUserLocalUseCase(
    scope: CoroutineScope,
    private val githubRepository: GithubRepository
) : UseCase<GitUserModel, GetSingleUserLocalUseCase.Params>(scope) {

    override fun run(params: Params?): Flow<GitUserModel> =
        githubRepository.getSingleUser(params!!.userId)

    data class Params(
        val userId: Long
    )
}