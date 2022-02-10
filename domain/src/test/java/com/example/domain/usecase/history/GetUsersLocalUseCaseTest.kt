package com.example.domain.usecase.history

import com.example.domain.factory.testFlow
import com.example.domain.factory.testModule
import com.example.domain.model.github.GitUserModel
import com.example.domain.repository.github.GithubRepository
import com.example.domain.usecase.github.history.GetUsersLocalUseCase
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

class GetUsersLocalUseCaseTest : KoinTest {

    @Mock
    private lateinit var response: List<GitUserModel>

    @Mock
    private lateinit var repository: GithubRepository
    private lateinit var subject: GetUsersLocalUseCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        subject = GetUsersLocalUseCase(
            scope = CoroutineScope(Dispatchers.Unconfined),
            repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `WHEN succeed MUST return Unit value`() {
        stubOnSuccess()
        subject.run().testFlow {
            assertEquals(response, this)
        }
    }

    @Test(expected = Exception::class)
    fun `WHEN fails MUST throw an exception`() {
        stubOnError()
        subject.run()
    }

    private fun stubOnSuccess() {
        whenever(repository.getGitUsers()).thenReturn(
            flowOf(response)
        )
    }

    private fun stubOnError() {
        whenever(repository.getGitUsers()).thenThrow(Exception())
    }
}