package com.example.feature_search.user_profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.utils.extensions.hideActionBar
import com.example.feature_search.R
import com.example.feature_search.commom.navigation.MobileNavigation
import com.example.feature_search.databinding.FragmentUserProfileBinding
import com.example.feature_search.databinding.FragmentUserProfileInfoBinding


class UserProfileInfoFragment : BaseFragment<FragmentUserProfileInfoBinding>() {


    override fun onCreateViewBinding(inflater: LayoutInflater): FragmentUserProfileInfoBinding =
        FragmentUserProfileInfoBinding.inflate(inflater)


    override fun setupView() {
        super.setupView()
        hideActionBar()

    }
}
