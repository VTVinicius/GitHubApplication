package com.example.di.navigation

import androidx.fragment.app.Fragment
import com.example.feature_search.commom.navigation.UserNavigation
import com.example.feature_search.commom.navigation.MobileNavigation
import com.example.navigation.navigators.feature_search.UserNavigationImpl
import com.example.navigation.navigators.feature_search.MobileNavigationImpl
import org.koin.dsl.module

val navigationSearchModel = module {

    factory<UserNavigation> { (fragment: Fragment) ->
        UserNavigationImpl(fragment)
    }
    factory<MobileNavigation> { (fragment: Fragment) ->
        MobileNavigationImpl(fragment)
    }

}