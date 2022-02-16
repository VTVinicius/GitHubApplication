package com.example.feature_search.commom.navigation

interface UserNavigation {

    companion object ArgNavigation {
        const val ARG_USER_NAME = "argUserName"
    }


    fun goBackToSearch()
    fun goToRepositories(name: String)
    fun goToFollowing(name: String)
    fun goToUserProfile(name: String)

}