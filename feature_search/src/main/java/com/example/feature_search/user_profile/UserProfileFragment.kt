package com.example.feature_search.user_profile

import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.utils.delegateproperties.navDirections
import com.example.feature_search.commom.navigation.MobileNavigation
import com.example.feature_search.databinding.FragmentUserProfileBinding
import com.example.uikit.extensions.loadUrl
import com.example.uikit.extensions.loadUrlWithCircular
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>() {

    private val viewModel: UserProfileViewModel by viewModel()

    override fun onCreateViewBinding(inflater: LayoutInflater): FragmentUserProfileBinding =
        FragmentUserProfileBinding.inflate(inflater)

    override fun setupView() {
        super.setupView()

        arguments?.getLong(MobileNavigation.ARG_USER_ID)?.let {
            viewModel.getSingleUser(it)
            onStateLoading()
        }

        hideLoading()



    }

    override fun addObservers(owner: LifecycleOwner) {
        super.addObservers(owner)
        loadUserObserver(owner)
    }

    private fun loadUserObserver(owner: LifecycleOwner) {
        viewModel.getSingleUserViewState.onPostValue(owner,
            onSuccess = { model ->
                binding.imgProfilePicture.loadUrl(model.gitUserData.user.avatar_url)
                binding.tvBio.text = model.gitUserData.user.bio ?: ""
                binding.tvLogin.text = model.gitUserData.user.login
                binding.tvName.text = model.gitUserData.user.name ?: ""

            },
            onError = {
                showErrorDialog()
            }
        )


    }


}