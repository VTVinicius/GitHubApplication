package com.example.uikit.list_github_user

import androidx.annotation.StringRes

class GithubListModel(
    @StringRes val id: Long,
    @StringRes val imageUrlRes: Int,
    @StringRes val loginRes: Int,
    @StringRes val nameRes: Int,
    @StringRes val bioRes: Int,
    @StringRes val emailRes: Int,
    var hasSelected: Boolean = false
)