package com.example.feature_search.search_user

import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import com.example.base_feature.core.BaseFragment
import com.example.base_feature.utils.extensions.hideKeyboard
import com.example.uikit.extensions.loadUrlWithCircular
import com.example.feature_search.R
import com.example.feature_search.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchUserFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchUserViewModel by viewModel()

    private var userLogin: String? = ""
    private var userName: String? = ""
    private var userBio: String? = ""
    private var userImage: String? = ""
    private var userEmail: String? = ""


    override fun onCreateViewBinding(inflater: LayoutInflater): FragmentSearchBinding =
        FragmentSearchBinding.inflate(inflater)

    override fun setupView() {
        super.setupView()

        binding.btnClick.setOnClickListener {
            val userName = binding.etUsernameInputText.text.toString()
            viewModel.searchUser(userName)
            onStateLoading()
            hideKeyboard()
        }
    }

    override fun addObservers(owner: LifecycleOwner) {
        super.addObservers(owner)
        searchUserObserver(owner)
    }

    private fun searchUserObserver(owner: LifecycleOwner) {

        viewModel.searchUserViewState.onPostValue(owner,
            onSuccess = { searchUserModel ->
                userLogin = searchUserModel.login
                userName = searchUserModel.name
                userBio = searchUserModel.bio ?: getString(R.string.no_bio)
                userEmail = searchUserModel.email ?: getString(R.string.no_email)
                userImage = searchUserModel.avatar_url
            },
            onError = {
                showErrorDialog()
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.resetViewState()
    }

}