package com.example.di

import com.example.feature_search.search_user.SearchUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { SearchUserViewModel() }

}