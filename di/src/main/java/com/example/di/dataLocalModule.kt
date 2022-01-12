package com.example.di

import com.example.data.datasource.local.GithubLocalDataSource
import com.example.data_local.datasource.GithubLocalDataSourceImpl
import org.koin.dsl.module

val dataLocalModule = module {

    single<GithubLocalDataSource> { GithubLocalDataSourceImpl(get()) }

}