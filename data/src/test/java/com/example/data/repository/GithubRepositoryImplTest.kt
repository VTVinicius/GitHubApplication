package com.example.data.repository

import com.example.data.datasource.local.GithubLocalDataSource
import com.example.data.datasource.remote.GithubRemoteDataSource
import com.example.data.factory.GithubFactory.DUMMY_GIT_USER_MODEL
import com.example.data.factory.GithubFactory.DUMMY_SEARCH_USER_MODEL
import com.example.data.factory.testFlow
import com.example.domain.repository.github.GithubRepository
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class GithubRepositoryImplTest {

    @Mock
    private lateinit var dataSource: GithubRemoteDataSource

    @Mock
    private lateinit var localDataSource: GithubLocalDataSource

    private lateinit var repository: GithubRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = GithubRepositoryImpl(dataSource, localDataSource)
    }


    @Test
    fun `searchUser should return SearchUserModel when success`() {
        stubSearchUserSuccess()

        repository.searchUser("mockk").testFlow {
            assertEquals(
                expected = DUMMY_SEARCH_USER_MODEL,
                actual = this
            )
        }
    }

    @Test
    fun `getGitUsers should return List of GitUserModel when success`() {
        stubGetGitUsersDataSuccess()

        repository.getGitUsers().testFlow {
            assertEquals(
                expected = listOf(DUMMY_GIT_USER_MODEL),
                actual = this
            )
        }
    }

    @Test
    fun `getSingleUserData should return GitUserModel when success`() {
        stubGetSingleUserDataSuccess()

        repository.getSingleUser(1).testFlow {
            assertEquals(
                expected = DUMMY_GIT_USER_MODEL,
                actual = this
            )
        }
    }

    @Test
    fun `saveGitUserData should return Nothing when success`() {
        stubSaveGitUserDataSuccess()

        assertEquals(
            expected = Unit,
            actual = Unit
        )
    }

    private fun stubSearchUserSuccess() {
        whenever(
            dataSource.searchUser("mockk")
        ).thenReturn(
            flowOf(
                DUMMY_SEARCH_USER_MODEL
            )
        )
    }

    private fun stubGetGitUsersDataSuccess() {
        whenever(
            localDataSource.getGitUserData()
        ).thenReturn(
            flowOf(
                listOf(
                    DUMMY_GIT_USER_MODEL
                )
            )
        )
    }

    private fun stubSaveGitUserDataSuccess() {
        doNothing().`when`(localDataSource).saveGitUserData(any())
    }

    private fun stubGetSingleUserDataSuccess() {
        whenever(
            localDataSource.getSingleUserData(1)
        ).thenReturn(
            flowOf(DUMMY_GIT_USER_MODEL)
        )

    }

}
