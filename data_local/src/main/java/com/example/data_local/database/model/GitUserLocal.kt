package com.example.data_local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data_local.database.Converter

@Entity(tableName = "GitUsersTable")
data class GitUserLocal(
    @TypeConverters(Converter::class) var gitUserData: GitUserDataLocal,
    @PrimaryKey val guid: Long
)