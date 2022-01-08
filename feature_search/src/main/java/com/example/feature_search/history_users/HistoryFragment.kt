package com.example.feature_search.history_users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.utils.extensions.hideKeyboard
import com.example.feature_search.databinding.FragmentHistoryBinding
import com.example.feature_search.databinding.FragmentSearchBinding
import com.example.feature_search.search_user.SearchUserViewModel
import com.example.uikit.list_github_user.GithubListUsersAdapter
import com.example.uikit.list_github_user.GithubSelectList
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.bind

class HistoryFragment : BaseFragment<FragmentHistoryBinding>(), GithubSelectList {

    private val viewModel: HistoryViewModel by viewModel()

    private lateinit var itemsRv : RecyclerView
    private lateinit var githubListUsersAdapter: GithubListUsersAdapter


    override fun onCreateViewBinding(inflater: LayoutInflater): FragmentHistoryBinding =
        FragmentHistoryBinding.inflate(inflater)


    override fun setupView() {
        super.setupView()

        itemsRv = binding.usersListRecyclerView
        githubListUsersAdapter = GithubListUsersAdapter(this)
        itemsRv.adapter = githubListUsersAdapter

        getUsersLocal()

    }


    override fun addObservers(owner: LifecycleOwner) {
        super.addObservers(owner)
        with(viewModel){
            getUsersLocalViewState.onPostValue(owner,
                onSuccess = { gitUserModel ->
//                 githubListUsersAdapter.userList = gitUserModel.map{}
                },
                onError = {

                }
            )
        }

    }

    private fun getUsersLocal(){
        onStateLoading()
        viewModel.getUsersLocal()


    }

}