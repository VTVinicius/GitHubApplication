package com.example.domain.usecase.search_user

import com.example.domain.exception.EmptyFieldException
import com.example.domain.exception.MissingParamsException
import com.example.domain.factory.testFlow
import com.example.domain.factory.testModule
import com.example.domain.model.github.SearchUserModel
import com.example.domain.repository.github.GithubRepository
import com.example.domain.usecase.github.search_user.SearchUserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class SearchUserUseCaseTest : KoinTest {

    @Mock
    private lateinit var response: SearchUserModel

    @Mock
    private lateinit var repository: GithubRepository
    private lateinit var subject: SearchUserUseCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        subject = SearchUserUseCase(
            scope = CoroutineScope(Dispatchers.Unconfined),
            repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test(expected = MissingParamsException::class)
    fun `WHEN don't receive params MUST throw MissingParamsException`() {
        subject.run()
    }

    @Test(expected = EmptyFieldException::class)
    fun `WHEN username is blank MUST throw EmptyFieldException`() {
        subject.run(
            params = SearchUserUseCase.Params(
                username = ""
            )
        )
    }

    @Test
    fun `WHEN succeed MUST return SearchUserModel value`() {
        stubOnSuccess()
        subject.run(SearchUserUseCase.Params("username")).testFlow {
            assertEquals(response, this)
        }
    }

    @Test(expected = Exception::class)
    fun `WHEN fails MUST throw an exception`() {
        stubOnError()
        subject.run()
    }

    private fun stubOnSuccess() {
        whenever(
            repository.searchUser("username")
        ).thenReturn(flowOf(response))
    }

    private fun stubOnError() {
        whenever(
            repository.searchUser("username")
        ).thenThrow(Exception())
    }
}