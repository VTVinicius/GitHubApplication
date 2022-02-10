package com.example.feature_search.search_user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfoModel(
    var id: Long,
    var login: String?,
    var avatar_url: String?,
    var bio: String?,
    var email: String?,
    var name: String?
): Parcelable