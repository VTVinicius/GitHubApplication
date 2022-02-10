package com.example.feature_search.search_user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.base_feature.utils.extensions.assertNeutral
import com.example.base_feature.utils.extensions.getOrAwaitValueAndAssert
import com.example.domain.model.github.SearchUserModel
import com.example.domain.usecase.github.search_user.SaveGitUserUseCase
import com.example.domain.usecase.github.search_user.SearchUserUseCase
import com.example.feature_search.search_user.SearchUserViewModelFactory.DUMMY_SEARCH_USER_MODEL
import io.mockk.every
import io.mockk.invoke
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class SearchUserViewModelTest {

    private lateinit var viewModel: SearchUserViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val searchUserUseCase: SearchUserUseCase = mockk()
    private val saveGitUserUseCase: SaveGitUserUseCase = mockk()

    private val testModule = module {
        single { searchUserUseCase }
        single { saveGitUserUseCase }
    }

    @Before
    fun before() {
        startKoin { modules(testModule) }
        viewModel = SearchUserViewModel()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun searchUserUseCaseWhenReturnSuccessOrError() {
        stubSaveGitUserUseCaseSuccess()
        stubSearchUserUseCaseSuccess()
        viewModel.searchUser("mockk")
        viewModel.searchUserViewState.getOrAwaitValueAndAssert()

        stubSearchUserUseCaseError()
        viewModel.searchUser("mockk")
        viewModel.searchUserViewState.getOrAwaitValueAndAssert(false)
    }

    private fun stubSearchUserUseCaseSuccess() {
        every {
            searchUserUseCase(
                params = SearchUserUseCase.Params("mockk"),
                onError = any(),
                onSuccess = captureLambda()
            )
        } answers {
            lambda<(SearchUserModel) -> Unit>().invoke(SearchUserModel())
        }
    }

    private fun stubSearchUserUseCaseError() {
        every {
            searchUserUseCase(
                params = any(), onError = captureLambda(), onSuccess = any()
            )
        } answers {
            lambda<(Throwable) -> Unit>().invoke(Throwable())
        }
    }

    @Test
    fun saveGitUserUseCaseWhenReturnSuccessOrError() {
        stubSaveGitUserUseCaseSuccess()
        viewModel.saveUserLocal(DUMMY_SEARCH_USER_MODEL)
        viewModel.saveGitUserViewSate.getOrAwaitValueAndAssert()

        stubSaveGitUserUseCaseError()
        viewModel.saveUserLocal(DUMMY_SEARCH_USER_MODEL)
        viewModel.saveGitUserViewSate.getOrAwaitValueAndAssert(false)
    }

    private fun stubSaveGitUserUseCaseSuccess() {
        every {
            saveGitUserUseCase(
                params = any(), onError = any(), onSuccess = captureLambda()
            )
        } answers {
            lambda<(Unit) -> Unit>().invoke(Unit)
        }
    }

    private fun stubSaveGitUserUseCaseError() {
        every {
            saveGitUserUseCase(
                params = any(), onError = captureLambda(), onSuccess = any()
            )
        } answers {
            lambda<(Throwable) -> Unit>().invoke(Throwable())
        }
    }

    @Test
    fun resetViewState() {
        viewModel.resetViewState()
        viewModel.searchUserViewState.assertNeutral()
        viewModel.saveGitUserViewSate.assertNeutral()
    }

}