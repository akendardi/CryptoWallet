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
class CreateAccountUseCaseTest {

    @Mock
    private lateinit var repository: AuthRepository

    private lateinit var createAccountUseCase: CreateAccountUseCase

    private lateinit var name: String

    private lateinit var email: String

    private lateinit var password: String

    @BeforeEach
    fun setUp() {
        createAccountUseCase = CreateAccountUseCase(repository)
    }

    @Test
    fun `should launch repository method`() = runBlocking {
        name = "name"
        email = "email"
        password = "password"
        createAccountUseCase(name, email, password)
        verify(repository, times(1)).createAccountWithEmail(name, email, password)
    }
}