package com.example.navigation.navigators.feature_search

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.feature_search.commom.navigation.UserNavigation
import com.example.navigation.R
import com.example.navigation.utils.navigate
import com.example.navigation.utils.popBackStack
import com.example.navigation.utils.setAnimSlideVerticalUp

class UserNavigationImpl(
    val fragment: Fragment
) : UserNavigation {

    override fun goBackToSearch() {
        fragment.popBackStack(R.id.mobile_navigation, false)
        fragment.navigate(R.id.homeFragment)
        fragment.navigate(R.id.historyFragment)
    }

    override fun goToRepositories(name: String) {
        fragment.navigate(
            R.id.action_userProfileFragment_to_userReposFragment,
            bundleOf(UserNavigation.ARG_USER_NAME to name),
            navOptions = setAnimSlideVerticalUp()
        )
    }

    override fun goToFollowing(name: String) {
            fragment.navigate(
            R.id.action_userProfileFragment_to_userFollowingFragment,
            bundleOf(UserNavigation.ARG_USER_NAME to name),
            navOptions = setAnimSlideVerticalUp()
        )
    }
    override fun goToUserProfile(name: String) {
            fragment.navigate(
            R.id.userProfileFragment,
            bundleOf(UserNavigation.ARG_USER_NAME to name),
            navOptions = setAnimSlideVerticalUp()
        )
    }

}