package com.example.domain.usecase.github.user_profile

import com.example.domain.core.UseCase
import com.example.domain.model.github.GitUserModel
import com.example.domain.repository.github.GithubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import java.lang.NullPointerException

class GetSingleUserLocalUseCase(
    scope: CoroutineScope,
    private val githubRepository: GithubRepository
) : UseCase<GitUserModel, GetSingleUserLocalUseCase.Params>(scope) {

    override fun run(params: Params?): Flow<GitUserModel> = when (params?.userId) {
        null -> throw NullPointerException()
        else -> githubRepository.getSingleUser(params.userId)
    }
    data class Params(
        val userId: Long
    )
}