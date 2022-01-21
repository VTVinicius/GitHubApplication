package com.example.domain.usecase.search_user

import com.example.domain.exception.MissingParamsException
import com.example.domain.factory.GithubFactory.DUMMY_GIT_USER_MODEL
import com.example.domain.factory.testFlow
import com.example.domain.factory.testModule
import com.example.domain.repository.github.GithubRepository
import com.example.domain.usecase.github.search_user.SaveGitUserUseCase
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

class SaveGitUserUseCaseTest : KoinTest {

    @Mock
    private lateinit var response: Unit

    @Mock
    private lateinit var repository: GithubRepository
    private lateinit var subject: SaveGitUserUseCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        subject = SaveGitUserUseCase(
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

    @Test
    fun `WHEN succeed MUST return Unit value`() {
        stubOnSuccess()
        subject.run(
            SaveGitUserUseCase.Params(
                DUMMY_GIT_USER_MODEL
            )
        ).testFlow {
            assertEquals(response, this)
        }
    }

    @Test(expected = Exception::class)
    fun `WHEN fails MUST throw an exception`() {
        stubOnError()
        subject.run(
            SaveGitUserUseCase.Params(DUMMY_GIT_USER_MODEL)
        )
    }

    private fun stubOnSuccess() {
        whenever(
            repository.saveGitUserData(DUMMY_GIT_USER_MODEL)
        ).thenReturn(flowOf(Unit))
    }

    private fun stubOnError() {
        whenever(repository.saveGitUserData(DUMMY_GIT_USER_MODEL)).thenThrow(Exception())
    }
}
