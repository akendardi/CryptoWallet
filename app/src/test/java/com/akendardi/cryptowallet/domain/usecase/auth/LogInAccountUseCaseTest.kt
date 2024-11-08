package com.akendardi.cryptowallet.domain.usecase.auth

import com.akendardi.cryptowallet.domain.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.times

@ExtendWith(MockitoExtension::class)
class LogInAccountUseCaseTest {

    @Mock
    private lateinit var repository: AuthRepository

    private lateinit var logInAccountUseCase: LogInAccountUseCase

    private lateinit var email: String
    private lateinit var password: String

    @BeforeEach
    fun setUp() {
        logInAccountUseCase = LogInAccountUseCase(repository)

    }

    @Test
    fun `should launch repository method`() = runBlocking {
        email = "email"
        password = "password"
        logInAccountUseCase(email, password)
        verify(repository, times(1)).logInAccount(email, password)
    }
}