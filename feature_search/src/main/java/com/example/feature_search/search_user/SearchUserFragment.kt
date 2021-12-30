package com.example.feature_search.search_user

import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.load.HttpException
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.utils.extensions.loadUrl
import com.example.feature_search.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchUserFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchUserViewModel by viewModel()
    private var userName = ""

    override fun onCreateViewBinding(inflater: LayoutInflater) =
        FragmentSearchBinding.inflate(inflater)


    override fun setupView() {
        super.setupView()

        binding.btnClick.setOnClickListener {
            userName = binding.etUsernameInputText.text.toString()
            viewModel.searchUser(userName)
            onStateLoading()
        }

    }

    override fun addObservers(owner: LifecycleOwner) {
        super.addObservers(owner)

        searchUserObserver(owner)
    }

    private fun searchUserObserver(owner: LifecycleOwner) {

        viewModel.searchUserViewState.onPostValue(owner,
            onSuccess = { searchUserModel ->
                binding.tvName.text = searchUserModel.login ?: "Sem Nome"
                binding.tvBio.text = searchUserModel.bio ?: "Sem Bio"
                binding.tvEmail.text = searchUserModel.email ?: "Sem Email"
                binding.imgProfilePic.loadUrl(url = searchUserModel.avatar_url)
            },
            onError = {
               showErrorDialog()
            })


    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}