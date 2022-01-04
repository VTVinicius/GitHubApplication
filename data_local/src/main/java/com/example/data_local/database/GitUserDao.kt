package com.example.data_local.database

import androidx.room.*
import com.example.data_local.database.model.GitUserDataLocal
import com.example.data_local.database.model.GitUserLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface GitUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(gitUser: GitUserLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addList(gitUsers: List<GitUserLocal>)

    @Query("SELECT * FROM GitUsersTable")
    fun getAll(): Flow<List<GitUserLocal>>

    @Query("SELECT * FROM GitUsersTable WHERE guid = :userID")
    fun getSingleUser(userID: Long): Flow<GitUserLocal>

    @Delete
    fun deleteUsers(vararg gitUsers: GitUserLocal)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(gitUser: GitUserLocal)

}