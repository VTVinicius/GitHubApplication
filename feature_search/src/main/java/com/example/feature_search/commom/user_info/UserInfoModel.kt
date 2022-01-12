package com.example.feature_search.commom.user_info

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfoModel(
    var login: String? = "",
    var avatar_url: String? = "",
    var bio: String? = "",
    var email: String? = "",
    var name: String? = ""
): Parcelable