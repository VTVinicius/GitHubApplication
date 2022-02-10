package com.example.feature_search.user_repos

import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.utils.delegateproperties.navDirections
import com.example.domain.model.github.UserReposModel
import com.example.feature_search.commom.navigation.MobileNavigation
import com.example.feature_search.commom.navigation.UserNavigation
import com.example.feature_search.databinding.FragmentUserReposBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserReposFragment : BaseFragment<FragmentUserReposBinding>() {

    private val viewModel: UserReposViewModel by viewModel()
    private val navigation: MobileNavigation by navDirections()

    private lateinit var userReposAdapter: UserReposAdapter

    override fun onCreateViewBinding(inflater: LayoutInflater): FragmentUserReposBinding =
        FragmentUserReposBinding.inflate(inflater)


    override fun setupView() {
        super.setupView()
        setUpReposCards()

        arguments?.getString(UserNavigation.ARG_USER_NAME)?.let { viewModel.getRepos(it) }

    }



    private fun setUpReposCards() {
        onStateLoading()
        userReposAdapter = UserReposAdapter(listOf())
        binding.reposListRecyclerView.apply {
            adapter = userReposAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }


    override fun addObservers(owner: LifecycleOwner) {
        super.addObservers(owner)

        listReposObserver(owner)
    }

    private fun listReposObserver(owner: LifecycleOwner) {
        viewModel.getUserReposViewState.onPostValue(owner,
            onSuccess = {
                updateUserList(it)
            },
            onError = {
                showErrorDialog()
            }
        )
    }

    private fun updateUserList(list: List<UserReposModel>) {
        userReposAdapter.updateReposList(list = list)
    }


}

