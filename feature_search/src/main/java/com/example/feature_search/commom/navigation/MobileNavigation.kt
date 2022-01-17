package com.example.feature_search.commom.navigation

interface MobileNavigation {

    companion object ArgMyCardFaqNavigation {
        const val ARG_USER_ID = "argUserId"
    }

    fun goToUserProfile(userId: Long)

}