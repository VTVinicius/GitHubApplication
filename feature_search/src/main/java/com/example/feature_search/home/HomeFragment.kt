package com.example.feature_search.home

import android.view.LayoutInflater
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.utils.extensions.showActionBar
import com.example.feature_search.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun onCreateViewBinding(inflater: LayoutInflater): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater)

    override fun setupView() {
        super.setupView()
        showActionBar()
    }
}