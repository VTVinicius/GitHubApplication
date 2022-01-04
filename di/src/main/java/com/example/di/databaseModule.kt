package com.example.di

import androidx.room.Room
import com.example.data_local.database.GithubDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            GithubDatabase::class.java,
            "user-database"
        ).build()
    }

    single { get<GithubDatabase>().gitUserDao() }

}