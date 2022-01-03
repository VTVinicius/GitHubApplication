package com.example.di

import com.example.domain.core.ThreadContextProvider
import com.example.domain.usecase.github.SaveGitUserUseCase
import com.example.domain.usecase.github.SearchUserUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {

    single {
        ThreadContextProvider()
    }

    factory { (scope: CoroutineScope) ->
        SearchUserUseCase(
            scope = scope,
            githubRepository = get()
        )
    }
    factory { (scope: CoroutineScope) ->
        SaveGitUserUseCase(
            scope = scope,
            githubRepository = get()
        )
    }

}