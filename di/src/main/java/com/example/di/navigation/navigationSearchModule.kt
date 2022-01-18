package com.example.di.navigation

import androidx.fragment.app.Fragment
import com.example.feature_search.commom.navigation.FeatureSearchNavigation
import com.example.feature_search.commom.navigation.MobileNavigation
import com.example.navigation.navigators.feature_search.FeatureSearchNavigationImpl
import com.example.navigation.navigators.feature_search.MobileNavigationImpl
import org.koin.dsl.module

val navigationSearchModel = module {

    factory<FeatureSearchNavigation> { (fragment: Fragment) ->
        FeatureSearchNavigationImpl(fragment)
    }
    factory<MobileNavigation> { (fragment: Fragment) ->
        MobileNavigationImpl(fragment)
    }

}