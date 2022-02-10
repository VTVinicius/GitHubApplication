package com.example.data_local.database

import android.text.TextUtils
import androidx.room.TypeConverter
import com.example.data_local.database.model.GitUserDataLocal
import com.google.gson.Gson

class Converter {

    @TypeConverter
    fun stringToUserData(string: String): GitUserDataLocal? {
        if (TextUtils.isEmpty(string))
            return null
        return Gson().fromJson(string, GitUserDataLocal::class.java)
    }

    @TypeConverter
    fun userDataToString(gitUserData: GitUserDataLocal): String {
        return Gson().toJson(gitUserData)
    }
}