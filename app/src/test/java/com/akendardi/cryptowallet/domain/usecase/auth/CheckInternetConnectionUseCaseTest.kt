package com.akendardi.cryptowallet.domain.usecase.auth

import com.akendardi.cryptowallet.domain.repository.InternetConnectionRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class CheckInternetConnectionUseCaseTest {

    @Mock
    private lateinit var internetConnectionRepository: InternetConnectionRepository
    private lateinit var checkInternetConnectionUseCase: CheckInternetConnectionUseCase

    @BeforeEach
    fun setUp() {
        checkInternetConnectionUseCase =
            CheckInternetConnectionUseCase(internetConnectionRepository)
    }

    @Test
    fun `should return true when internet connection is available`() = runBlocking {
        whenever(internetConnectionRepository.checkInternetConnection()).thenReturn(true)

        val result = checkInternetConnectionUseCase.invoke()

        assertEquals(true, result)
    }

    @Test
    fun `should return false when internet connection is not available`() = runBlocking {
        whenever(internetConnectionRepository.checkInternetConnection()).thenReturn(false)

        val result = checkInternetConnectionUseCase.invoke()

        assertEquals(false, result)
    }

}