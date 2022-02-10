package com.example.feature_search.user_profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.base_feature.utils.extensions.getOrAwaitValueAndAssert
import com.example.domain.model.github.GitUserModel
import com.example.domain.usecase.github.user_profile.GetSingleUserLocalUseCase
import com.example.feature_search.user_profile.UserProfileViewModelFactory.DUMMY_USER_ID
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

class UserProfileViewModelTest {

    private lateinit var viewModel: UserProfileViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val getSingleUserLocalUseCase: GetSingleUserLocalUseCase = mockk()

    private val testModule = module {
        single { getSingleUserLocalUseCase }
    }

    @Before
    fun before() {
        startKoin { modules(testModule) }
        viewModel = UserProfileViewModel()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun getSingleUserUseCaseWhenReturnSuccessOrError() {
        stubGetSingleUserUseCaseSuccess()
        viewModel.getSingleUser(DUMMY_USER_ID)
        viewModel.getSingleUserViewState.getOrAwaitValueAndAssert()

        stubGetSingleUserUseCaseError()
        viewModel.getSingleUser(DUMMY_USER_ID)
        viewModel.getSingleUserViewState.getOrAwaitValueAndAssert(false)
    }

    private fun stubGetSingleUserUseCaseSuccess() {
        every {
            getSingleUserLocalUseCase(
                params = GetSingleUserLocalUseCase.Params(DUMMY_USER_ID),
                onError = any(),
                onSuccess = captureLambda()
            )
        } answers {
            lambda<(GitUserModel) -> Unit>().invoke(GitUserModel())
        }
    }

    private fun stubGetSingleUserUseCaseError() {
        every {
            getSingleUserLocalUseCase(
                params = any(), onError = captureLambda(), onSuccess = any()
            )
        } answers {
            lambda<(Throwable) -> Unit>().invoke(Throwable())
        }
    }
}