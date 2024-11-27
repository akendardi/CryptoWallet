package com.akendardi.cryptowallet.domain.usecase.auth

import com.akendardi.cryptowallet.domain.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.times

class ResetPasswordUseCaseTest{

    @Mock
    private lateinit var repository: AuthRepository

    private lateinit var resetPasswordUseCase: ResetPasswordUseCase

    private lateinit var email: String

    @BeforeEach
    fun setUp() {
        resetPasswordUseCase = ResetPasswordUseCase(repository)
    }

    @Test
    fun `should launch repository method`() = runBlocking {
        email = "email"
        resetPasswordUseCase(email)
        verify(repository, times(1)).resetPasswordWithEmail(email)
    }
}