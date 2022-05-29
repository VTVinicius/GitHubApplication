package com.example.feature_search.user_profile.pages.infos

import android.view.LayoutInflater
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.utils.extensions.hideActionBar
import com.example.feature_search.databinding.FragmentUserProfileInfoBinding


class UserProfileInfoFragment : BaseFragment<FragmentUserProfileInfoBinding>() {


    override fun onCreateViewBinding(inflater: LayoutInflater): FragmentUserProfileInfoBinding =
        FragmentUserProfileInfoBinding.inflate(inflater)


    override fun setupView() {
        super.setupView()
        hideActionBar()

    }

}
