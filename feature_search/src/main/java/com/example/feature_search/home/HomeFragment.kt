package com.example.feature_search.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.utils.extensions.showActionBar
import com.example.feature_search.databinding.FragmentHistoryBinding
import com.example.feature_search.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun onCreateViewBinding(inflater: LayoutInflater): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater)


    override fun setupView() {
        super.setupView()
        showActionBar()

    }

}