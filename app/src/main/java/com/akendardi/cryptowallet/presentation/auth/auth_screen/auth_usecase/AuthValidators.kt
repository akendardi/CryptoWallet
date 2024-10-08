package com.akendardi.cryptowallet.presentation.auth.auth_screen.auth_usecase

import android.util.Patterns
import javax.inject.Inject

class UserNameValidator @Inject constructor() {
    operator fun invoke(userName: String): UsernameValidationResult {
        return if (userName.isNotBlank()) UsernameValidationResult.CORRECT
        else UsernameValidationResult.IS_EMPTY
    }
}

class PasswordValidator @Inject constructor() {
    operator fun invoke(password: String): PasswordValidationResult {
        return if (password.length < 8) PasswordValidationResult.NOT_LONG_ENOUGH
        else if (password.count(Char::isUpperCase) == 0) PasswordValidationResult.NOT_ENOUGH_UPPERCASE
        else if (!password.contains("[0-9]".toRegex())) PasswordValidationResult.NOT_ENOUGH_DIGITS
        else PasswordValidationResult.CORRECT
    }
}

class EmailValidator @Inject constructor() {
    operator fun invoke(email: String): EmailValidationResult {
        return if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EmailValidationResult.CORRECT
        } else EmailValidationResult.INCORRECT_FORMAT
    }
}

enum class UsernameValidationResult {
    IS_EMPTY,
    CORRECT
}

enum class EmailValidationResult {
    INCORRECT_FORMAT,
    CORRECT
}

enum class PasswordValidationResult {
    NOT_LONG_ENOUGH,
    NOT_ENOUGH_DIGITS,
    NOT_ENOUGH_UPPERCASE,
    CORRECT
}