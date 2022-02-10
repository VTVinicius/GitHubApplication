package com.example.feature_search.commom.navigation

interface MobileNavigation {

    companion object ArgNavigation {
        const val ARG_USER_ID = "argUserId"
    }

    fun goToUserProfile(userId: Long)
    fun goBackToUserProfile()
}