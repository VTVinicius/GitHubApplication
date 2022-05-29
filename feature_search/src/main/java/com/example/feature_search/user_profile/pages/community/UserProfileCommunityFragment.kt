package com.example.feature_search.user_profile.pages.community

import android.view.LayoutInflater
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.utils.extensions.hideActionBar
import com.example.feature_search.databinding.FragmentUserProfileCommunityBinding


class UserProfileCommunityFragment : BaseFragment<FragmentUserProfileCommunityBinding>() {


    override fun onCreateViewBinding(inflater: LayoutInflater): FragmentUserProfileCommunityBinding =
        FragmentUserProfileCommunityBinding.inflate(inflater)


    override fun setupView() {
        super.setupView()
        hideActionBar()

    }
}