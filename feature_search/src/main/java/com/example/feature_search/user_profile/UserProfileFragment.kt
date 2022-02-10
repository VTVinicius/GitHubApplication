package com.example.feature_search.user_profile

import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.utils.delegateproperties.navDirections
import com.example.base_feature.utils.extensions.hideActionBar
import com.example.feature_search.commom.navigation.MobileNavigation
import com.example.feature_search.commom.navigation.UserNavigation
import com.example.feature_search.databinding.FragmentUserProfileBinding
import com.example.uikit.extensions.loadUrlWithCircular
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>() {

    private val viewModel: UserProfileViewModel by viewModel()
    private val navigation: UserNavigation by navDirections()

    override fun onCreateViewBinding(inflater: LayoutInflater): FragmentUserProfileBinding =
        FragmentUserProfileBinding.inflate(inflater)


    override fun setupView() {
        super.setupView()
        hideActionBar()

        arguments?.getLong(MobileNavigation.ARG_USER_ID)?.let { viewModel.getSingleUser(it) }
        onClickFun()
    }


    private fun onClickFun() {
        binding.btnClose.setOnClickListener {
            navigation.goBackToSearch()
        }

        binding.reposCardView.setOnClickListener {
            navigation.goToRepositories(binding.tvLogin.text.toString())
        }
    }

    override fun addObservers(owner: LifecycleOwner) {
        super.addObservers(owner)
        loadUserObserver(owner)
        loadUserFollowersObserver(owner)
        loadUserFollowingObserver(owner)
        loadUserReposObserver(owner)

    }

    private fun loadUserObserver(owner: LifecycleOwner) {
        viewModel.getSingleUserViewState.onPostValue(owner,
            onSuccess = { model ->
                binding.imgProfilePicture.loadUrlWithCircular(model.gitUserData.user.avatar_url)
                binding.tvBio.text = model.gitUserData.user.bio ?: ""
                binding.tvLogin.text = model.gitUserData.user.login
                binding.tvName.text = model.gitUserData.user.name ?: ""


                viewModel.getUserFollowers(model.gitUserData.user.login ?: "")
                viewModel.getUserFollowing(model.gitUserData.user.login ?: "")
                viewModel.getNumberRepos(model.gitUserData.user.login ?: "")
                onStateLoading()
            },
            onError = {
                showErrorDialog()
            }
        )
    }

    private fun loadUserFollowersObserver(owner: LifecycleOwner) {
        viewModel.getUserFollowersViewState.onPostValue(owner,
            onSuccess = {
                binding.tvNumberFollowers.text = it.size.toString()
            }
        )
    }

    private fun loadUserFollowingObserver(owner: LifecycleOwner) {
        viewModel.getUserFollowingViewState.onPostValue(owner,
            onSuccess = {
                binding.tvNumberFollowing.text = it.size.toString()
            }
        )
    }

    private fun loadUserReposObserver(owner: LifecycleOwner) {
        viewModel.getUserReposViewState.onPostValue(owner,
            onSuccess = {
                binding.reposNumberTv.text = it.size.toString()
            }
        )
    }
}