package com.example.feature_search.commom.user_info

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfoModel(
    val login: String? = "",
    val avatar_url: String? = "",
    val bio: String? = "",
    val email: String? = "",
    val name: String? = ""
): Parcelable