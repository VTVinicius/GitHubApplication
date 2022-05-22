package com.example.feature_search.user_profile

import android.view.LayoutInflater
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.utils.extensions.hideActionBar
import com.example.feature_search.databinding.FragmentUserProfileCommunityBinding
import com.example.feature_search.databinding.FragmentUserProfileRepositoriesBinding

class UserProfileRepositoriesFragment : BaseFragment<FragmentUserProfileRepositoriesBinding>() {


    override fun onCreateViewBinding(inflater: LayoutInflater): FragmentUserProfileRepositoriesBinding =
        FragmentUserProfileRepositoriesBinding.inflate(inflater)


    override fun setupView() {
        super.setupView()
        hideActionBar()

    }
}