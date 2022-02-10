package com.example.di

import com.example.domain.core.ThreadContextProvider
import com.example.domain.usecase.github.history.GetUsersLocalUseCase
import com.example.domain.usecase.github.search_user.SaveGitUserUseCase
import com.example.domain.usecase.github.search_user.SearchUserUseCase
import com.example.domain.usecase.github.user_profile.GetSingleUserLocalUseCase
import com.example.domain.usecase.github.user_profile.GetUserFollowersUseCase
import com.example.domain.usecase.github.user_profile.GetUserFollowingUseCase
import com.example.domain.usecase.github.user_repos.GetUserReposUseCase
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
    factory { (scope: CoroutineScope) ->
        GetUsersLocalUseCase(
            scope = scope,
            githubRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        GetSingleUserLocalUseCase(
            scope = scope,
            githubRepository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        GetUserFollowersUseCase(
            scope = scope,
            githubRepository = get()
        )
    }
    factory { (scope: CoroutineScope) ->
        GetUserFollowingUseCase(
            scope = scope,
            githubRepository = get()
        )
    }
    factory { (scope: CoroutineScope) ->
        GetUserReposUseCase(
            scope = scope,
            githubRepository = get()
        )
    }

}