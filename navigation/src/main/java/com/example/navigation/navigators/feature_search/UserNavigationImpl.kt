package com.example.navigation.navigators.feature_search

import androidx.fragment.app.Fragment
import com.example.feature_search.commom.navigation.MobileNavigation
import com.example.feature_search.commom.navigation.UserNavigation
import com.example.navigation.R
import com.example.navigation.utils.navigate

class UserNavigationImpl(
    val fragment: Fragment
) : UserNavigation, MobileNavigation {

    override fun goBackToSearch() {
        fragment.navigate(
            R.id.searchUserFragment
        )
    }

    override fun goToUserProfile(userId: Long) {
        TODO("Not yet implemented")
    }

}