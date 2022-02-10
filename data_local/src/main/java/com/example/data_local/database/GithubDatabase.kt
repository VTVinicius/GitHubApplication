package com.example.data_local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data_local.database.model.GitUserLocal

@Database(entities = [GitUserLocal::class], version = 1)
@TypeConverters(Converter::class)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun gitUserDao(): GitUserDao
}
