package com.example.di

import com.example.feature_search.history_users.HistoryViewModel
import com.example.feature_search.search_user.SearchUserViewModel
import com.example.feature_search.user_profile.UserProfileViewModel
import com.example.feature_search.user_repos.UserReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { UserProfileViewModel() }
    viewModel { SearchUserViewModel() }
    viewModel { HistoryViewModel() }
    viewModel { UserReposViewModel() }

}