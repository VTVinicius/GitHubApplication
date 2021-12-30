package com.example.di

import com.example.data.repository.GithubRepositoryImpl
import com.example.domain.repository.github.GithubRepository
import org.koin.dsl.module

val dataModule = module {

    single<GithubRepository> { GithubRepositoryImpl(get()) }

}