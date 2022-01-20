package com.example.navigation.navigators.feature_search

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.feature_search.commom.navigation.MobileNavigation
import com.example.feature_search.commom.navigation.UserNavigation
import com.example.navigation.R
import com.example.navigation.utils.navigate

class MobileNavigationImpl(
        val fragment: Fragment
    ) : MobileNavigation, UserNavigation{

    override fun goToUserProfile(userId: Long) {
        fragment.navigate(
            R.id.userProfileFragment,
            bundleOf(MobileNavigation.ARG_USER_ID to userId)
        )
    }

    override fun goBackToSearch() {
        fragment.navigate(
            R.id.searchUserFragment
        )

    }


}
