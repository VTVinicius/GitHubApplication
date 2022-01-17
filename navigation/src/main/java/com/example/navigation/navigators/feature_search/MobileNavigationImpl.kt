package com.example.navigation.navigators.feature_search

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.feature_search.commom.navigation.FeatureSearchNavigation
import com.example.feature_search.commom.navigation.MobileNavigation
import com.example.navigation.R
import com.example.navigation.utils.navigate

class MobileNavigationImpl(
        val fragment: Fragment
    ) : MobileNavigation {

    override fun goToUserProfile(userId: Long) {
        fragment.navigate(
            R.id.userProfileFragment
        )
        bundleOf(MobileNavigation.ARG_USER_ID to userId)
    }


}
