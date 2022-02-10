package com.example.data_remote.datasource

import com.example.data_remote.factory.GithubFactory.DUMMY_SEARCH_USER_RESPONSE
import com.example.data_remote.mapper.github.SearchUserMapper
import com.example.data_remote.model.search_user.SearchUserResponse
import com.example.data_remote.service.GithubWebService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class GithubRemoteDataSourceImplTest {

    private val testModule = module {
        single { githubWebService }
    }

    private lateinit var dataSource: GithubRemoteDataSourceImpl

    private var githubWebService: GithubWebService = mockk(relaxed = true)

    @Before
    fun setup() {
        startKoin { modules(testModule) }
        dataSource = GithubRemoteDataSourceImpl(githubWebService)
    }

    @After
    fun afterTest() {
        stopKoin()
    }

    @Test
    fun searchUserShouldReturnSearchUserResponseWhenCall() = runBlocking {
        val response: SearchUserResponse = DUMMY_SEARCH_USER_RESPONSE

        stubSearchUser(response)

        val result = dataSource.searchUser("mock").first()
        val mapperData = SearchUserMapper.toDomain(data = DUMMY_SEARCH_USER_RESPONSE)

        Assert.assertEquals(mapperData, result)
    }

    private suspend fun stubSearchUser(
        response: SearchUserResponse
    ) {
        coEvery {
            (githubWebService.searchUser("mock"))
        } returns response
    }
}