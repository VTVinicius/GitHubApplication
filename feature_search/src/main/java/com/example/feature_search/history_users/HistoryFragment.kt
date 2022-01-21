package com.example.feature_search.history_users

import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.utils.delegateproperties.navDirections
import com.example.base_feature.utils.extensions.showActionBar
import com.example.domain.model.github.GitUserModel
import com.example.feature_search.commom.navigation.MobileNavigation
import com.example.feature_search.databinding.FragmentHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private val viewModel: HistoryViewModel by viewModel()
    private val navigation: MobileNavigation by navDirections()

    private lateinit var githubListUsersAdapter: GithubListUsersAdapter

    override fun onCreateViewBinding(inflater: LayoutInflater): FragmentHistoryBinding =
        FragmentHistoryBinding.inflate(inflater)

    override fun setupView() {
        super.setupView()
        showActionBar()

        getUsersLocal()
        setUpUserCards()
    }

    private fun getUsersLocal() {
        onStateLoading()
        viewModel.getUsersLocal()
    }

    private fun setUpUserCards() {
        onStateLoading()
        githubListUsersAdapter = GithubListUsersAdapter(listOf(), navigation::goToUserProfile)
        binding.usersListRecyclerView.apply {
            adapter = githubListUsersAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun addObservers(owner: LifecycleOwner) {
        super.addObservers(owner)

        listUsersObserver(owner)
    }

    private fun listUsersObserver(owner: LifecycleOwner) {
        viewModel.getUsersLocalViewState.onPostValue(owner,
            onSuccess = {
                updateUserList(it)
            },
            onError = {
                showErrorDialog()
            }
        )
    }

    private fun updateUserList(list: List<GitUserModel>) {
        githubListUsersAdapter.updateUserList(list = list)
    }
}
