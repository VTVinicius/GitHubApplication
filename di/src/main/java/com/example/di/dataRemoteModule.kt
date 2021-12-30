package com.example.di

import com.example.data.datasource.remote.GithubRemoteDataSource
import com.example.data_remote.datasource.GithubRemoteDataSourceImpl
import com.example.data_remote.service.GithubWebService
import com.example.data_remote.utils.GITHUB_API_URL
import com.example.data_remote.utils.WebServiceFactory
import org.koin.dsl.bind
import org.koin.dsl.module

val dataRemoteModule = module {

    single {
        WebServiceFactory.provideOkHttpClient(
            wasDebugVersion = true
        )
    }

    single {
        WebServiceFactory.createWebService(
            get(),
            url = GITHUB_API_URL
        ) as GithubWebService
    }

    single<GithubRemoteDataSource> { GithubRemoteDataSourceImpl(get()) }
}