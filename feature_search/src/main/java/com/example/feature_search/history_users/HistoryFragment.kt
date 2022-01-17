package com.example.feature_search.history_users

import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base_feature.core.BaseFragment
import com.example.domain.model.github.GitUserModel
import com.example.feature_search.databinding.FragmentHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private val viewModel: HistoryViewModel by viewModel()

    private lateinit var githubListUsersAdapter: GithubListUsersAdapter


    override fun onCreateViewBinding(inflater: LayoutInflater): FragmentHistoryBinding =
        FragmentHistoryBinding.inflate(inflater)


    override fun setupView() {
        super.setupView()

        getUsersLocal()
        setUpUserCards()

    }

    private fun getUsersLocal() {
        onStateLoading()
        viewModel.getUsersLocal()

    }

    private fun setUpUserCards() {
        onStateLoading()
        githubListUsersAdapter = GithubListUsersAdapter(listOf()) //, this::onItemClick
        binding.usersListRecyclerView.apply {
            adapter = githubListUsersAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

//    private fun onItemClick(userDetails: GitUserModel) {
//        navigation.goToUserFullScreen(
//            userDetails = userDetails.toJson()
//        )
//    }

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

//
//            private fun paymentHistoricObserver(owner: LifecycleOwner) {
//        viewModel.paymentHistoricViewState.onPostValue(owner,
//            onLoading = {
//                binding.listLoadingLottie.setVisible()
//                binding.paymentHistoricPlaceHolder.root.setGone()
//                binding.rvPaymentHistoric.setGone()
//            },
//            onSuccess = {
//                binding.listLoadingLottie.setGone()
//                if (it.isEmpty()) {
//                    binding.paymentHistoricPlaceHolder.apply {
//                        tvPlaceHolderTitle.text = getString(R.string.payment_historic_placeholder)
//                        root.setVisible()
//                    }
//                    binding.viewHeaderBackground.setGone()
//                    binding.imageView.setGone()
//                } else {
//                    binding.paymentHistoricPlaceHolder.root.setGone()
//                    binding.rvPaymentHistoric.setVisible()
//                    setupCarousel(data = viewModel.paymentHistoricYearList)
//                    updatePaymentHistoricList(
//                        list = viewModel.getPaymentHistoricBasedOnYear() ?: listOf()
//                    )
//                }
//            },
//            onError = {
//                binding.listLoadingLottie.setGone()
//            }
//        )
//    }
//
//    with(viewModel) {
//            getUsersLocalViewState.onPostValue(owner,
//                onSuccess = {
//
//                },
//                onError = {
//                    showErrorDialog()
//                }
//            )
//        }

}
