package com.akendardi.cryptowallet.domain.usecase.auth

import com.akendardi.cryptowallet.domain.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class LogOutFromAccountUseCaseTest {

    private var repository: AuthRepository = mock<AuthRepository>()

    private lateinit var logOutFromAccountUseCase: LogOutFromAccountUseCase


    @BeforeEach
    fun setUp() {
        logOutFromAccountUseCase = LogOutFromAccountUseCase(repository)
    }

    @Test
    fun `should launch repository method`() = runBlocking {
        logOutFromAccountUseCase()
        verify(repository, times(1)).logOutFromAccount()
    }
}