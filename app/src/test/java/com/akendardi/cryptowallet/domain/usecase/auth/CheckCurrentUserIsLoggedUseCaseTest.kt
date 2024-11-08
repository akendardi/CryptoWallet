package com.akendardi.cryptowallet.domain.usecase.auth

import com.akendardi.cryptowallet.domain.repository.AuthRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class CheckCurrentUserIsLoggedUseCaseTest {

    private var repository: AuthRepository = mock<AuthRepository>()

    private lateinit var checkCurrentUserIsLoggedUseCase: CheckCurrentUserIsLoggedUseCase

    @BeforeEach
    fun setUp() {
        checkCurrentUserIsLoggedUseCase = CheckCurrentUserIsLoggedUseCase(repository)
    }


    @Test
    fun `invoke should return true when user is logged in`() = runBlocking {
        whenever(repository.checkCurrentUserIsLogged()).thenReturn(true)

        val result = checkCurrentUserIsLoggedUseCase()

        assertThat(result).isTrue()

    }

    @Test
    fun `should launch repository method`(): Unit = runBlocking {
        checkCurrentUserIsLoggedUseCase()
        verify(repository, times(1)).checkCurrentUserIsLogged()
    }
}