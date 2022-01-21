package com.example.navigation.navigators.feature_search

import androidx.fragment.app.Fragment
import com.example.feature_search.commom.navigation.UserNavigation
import com.example.navigation.R
import com.example.navigation.utils.navigate
import com.example.navigation.utils.popBackStack

class UserNavigationImpl(
    val fragment: Fragment
) : UserNavigation {

    override fun goBackToSearch() {
        fragment.popBackStack(R.id.mobile_navigation, false)
        fragment.navigate(R.id.homeFragment)
        fragment.navigate(R.id.historyFragment)
    }
}