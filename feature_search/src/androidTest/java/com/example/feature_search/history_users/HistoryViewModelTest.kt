package com.example.feature_search.history_users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.base_feature.utils.extensions.getOrAwaitValueAndAssert
import com.example.domain.model.github.GitUserModel
import com.example.domain.usecase.github.history.GetUsersLocalUseCase
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

class HistoryViewModelTest {


    private lateinit var viewModel: HistoryViewModel


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val getUsersLocalUseCase: GetUsersLocalUseCase = mockk()

    private val testModule = module {
        single { getUsersLocalUseCase }
    }

    @Before
    fun before() {
        startKoin { modules(testModule) }
        viewModel = HistoryViewModel()
    }

    @After
    fun after() {
        stopKoin()
    }


    @Test
    fun searchUserUseCaseWhenReturnSuccessOrError() {
        stubGetUsersLocalUseCaseSuccess()
        viewModel.getUsersLocal()
        viewModel.getUsersLocalViewState.getOrAwaitValueAndAssert()

        stubGetUsersLocalUseCaseError()
        viewModel.getUsersLocal()
        viewModel.getUsersLocalViewState.getOrAwaitValueAndAssert(false)

    }

    private fun stubGetUsersLocalUseCaseSuccess() {
        every {
            getUsersLocalUseCase(
                onError = any(), onSuccess = captureLambda()
            )
        } answers {
            lambda<(List<GitUserModel>) -> Unit>().invoke(listOf<GitUserModel>())
        }
    }

    private fun stubGetUsersLocalUseCaseError() {
        every {
            getUsersLocalUseCase(
                onError = captureLambda(), onSuccess = any()
            )
        } answers {
            lambda<(Throwable) -> Unit>().invoke(Throwable())
        }
    }
}
