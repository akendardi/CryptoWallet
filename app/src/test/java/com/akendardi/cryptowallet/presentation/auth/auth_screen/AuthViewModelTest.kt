package com.akendardi.cryptowallet.presentation.auth.auth_screen

import com.akendardi.cryptowallet.domain.repository.AuthRepository
import com.akendardi.cryptowallet.domain.usecase.auth.CreateAccountUseCase
import com.akendardi.cryptowallet.domain.usecase.auth.LogInAccountUseCase
import com.akendardi.cryptowallet.domain.usecase.auth.ResetPasswordUseCase
import com.akendardi.cryptowallet.domain.usecase.validators.EmailValidator
import com.akendardi.cryptowallet.domain.usecase.validators.PasswordValidator
import com.akendardi.cryptowallet.domain.usecase.validators.UserNameValidator
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class AuthViewModelTest {
    @Mock
    private lateinit var logInAccountUseCase: LogInAccountUseCase

    @Mock
    private lateinit var createAccountUseCase: CreateAccountUseCase

    @Mock
    private lateinit var resetPasswordUseCase: ResetPasswordUseCase

    @Mock
    private lateinit var passwordValidator: PasswordValidator

    @Mock
    private lateinit var emailValidator: EmailValidator

    @Mock
    private lateinit var userNameValidator: UserNameValidator

    @Mock
    private lateinit var repository: AuthRepository

    private lateinit var authViewModel: AuthViewModel

    @BeforeEach
    fun setUp() {
        authViewModel = AuthViewModel(
            logInAccountUseCase,
            createAccountUseCase,
            resetPasswordUseCase,
            passwordValidator,
            emailValidator,
            userNameValidator,
            repository
        )
    }

    @Test
    fun `should call create account use case when create account button is clicked with correct data`() =
        runBlocking {
            authViewModel.onUsername("abc")
            authViewModel.onEmail("abc@mail.com")
            authViewModel.onPassword("abcAAAss123")

            authViewModel.changeAuthType(AuthType.SIGN_UP)

            authViewModel.sendAuthRequest()

            verify(createAccountUseCase).invoke("abc", "abc@mail.com", "abcAAAss123")
        }
}