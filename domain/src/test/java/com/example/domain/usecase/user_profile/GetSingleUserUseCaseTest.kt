package com.example.domain.usecase.user_profile

import com.example.domain.mocks.GithubFactory.DUMMY_USERID_VALUE
import com.example.domain.mocks.testFlow
import com.example.domain.mocks.testModule
import com.example.domain.model.github.GitUserModel
import com.example.domain.model.github.SearchUserModel
import com.example.domain.repository.github.GithubRepository
import com.example.domain.usecase.github.search_user.SearchUserUseCase
import com.example.domain.usecase.github.user_profile.GetSingleUserLocalUseCase
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
import org.mockito.internal.matchers.Null
import org.mockito.kotlin.whenever
import java.lang.NullPointerException
import kotlin.test.assertEquals

class GetSingleUserUseCaseTest : KoinTest {

    @Mock
    private lateinit var response: GitUserModel

    @Mock
    private lateinit var repository: GithubRepository
    private lateinit var subject: GetSingleUserLocalUseCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        subject = GetSingleUserLocalUseCase(
            scope = CoroutineScope(Dispatchers.Unconfined),
            repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }


    @Test(expected = NullPointerException::class)
    fun `WHEN params is Null MUST throw NullPointerException`() {
        subject.run()
    }

    @Test
    fun `WHEN succeed MUST return GitUserModel value`() {
        stubOnSuccess()
        subject.run(GetSingleUserLocalUseCase.Params(userId = DUMMY_USERID_VALUE)).testFlow {
            assertEquals(response, this)
        }
    }

    @Test(expected = Exception::class)
    fun `WHEN fails MUST throw an exception`() {
        stubOnError()
        subject.run()
    }


    private fun stubOnSuccess() {
        whenever(repository.getSingleUser(DUMMY_USERID_VALUE)).thenReturn(
            flowOf(response)
        )
    }

    private fun stubOnError() {
        whenever(repository.getSingleUser(DUMMY_USERID_VALUE)).thenThrow(Exception())
    }


}