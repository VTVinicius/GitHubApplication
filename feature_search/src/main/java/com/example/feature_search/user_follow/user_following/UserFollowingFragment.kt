package com.example.feature_search.user_follow.user_following

import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.utils.delegateproperties.navDirections
import com.example.base_feature.utils.extensions.showActionBar
import com.example.domain.model.github.SearchUserModel
import com.example.feature_search.commom.navigation.MobileNavigation
import com.example.feature_search.commom.navigation.UserNavigation
import com.example.feature_search.databinding.FragmentUserFollowingBinding
import com.example.feature_search.user_follow.UserFollowAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFollowingFragment : BaseFragment<FragmentUserFollowingBinding>() {

    private val viewModel: UserFollowingViewModel by viewModel()
    private val navigation: UserNavigation by navDirections()

    private lateinit var userFollowAdapter: UserFollowAdapter


    override fun onCreateViewBinding(inflater: LayoutInflater): FragmentUserFollowingBinding =
        FragmentUserFollowingBinding.inflate(inflater)


    override fun setupView() {
        super.setupView()
        showActionBar()

        arguments?.getString(UserNavigation.ARG_USER_NAME)?.let { viewModel.getUserFollowing(it) }

        setUpUserCards()
    }

    private fun setUpUserCards() {
        onStateLoading()
        userFollowAdapter = UserFollowAdapter(listOf(), navigation::goToUserProfile)
        binding.usersListRecyclerView.apply {
            adapter = userFollowAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun addObservers(owner: LifecycleOwner) {
        super.addObservers(owner)

        listUsersObserver(owner)
    }

    private fun listUsersObserver(owner: LifecycleOwner) {
        viewModel.getUserFollowingViewState.onPostValue(owner,
            onSuccess = {
                updateUserList(it)
            },
            onError = {
                showErrorDialog()
            }
        )
    }

    private fun updateUserList(list: List<SearchUserModel>) {
        userFollowAdapter.updateUserList(list = list)
    }

}