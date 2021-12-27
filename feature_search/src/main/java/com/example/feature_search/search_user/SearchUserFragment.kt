package com.example.feature_search.search_user

import android.view.LayoutInflater
import com.example.base_feature.core.BaseFragment
import com.example.feature_search.databinding.FragmentSearchBinding

class SearchUserFragment : BaseFragment<FragmentSearchBinding>() {

    override fun onCreateViewBinding(inflater: LayoutInflater) =
        FragmentSearchBinding.inflate(inflater)


    override fun setupView() {
        super.setupView()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        //viewmodel
    }

}