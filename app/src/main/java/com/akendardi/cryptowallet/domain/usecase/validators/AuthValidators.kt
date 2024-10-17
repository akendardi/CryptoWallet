package com.akendardi.cryptowallet.domain.usecase.validators

import android.content.Context
import android.util.Patterns
import com.akendardi.cryptowallet.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserNameValidator @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke(userName: String): UsernameValidationResult {
        return if (userName.isNotBlank()) UsernameValidationResult.CORRECT
        else UsernameValidationResult.IS_EMPTY
    }

    fun getUsernameError(usernameValidationResult: UsernameValidationResult): String {
        return when (usernameValidationResult) {
            UsernameValidationResult.IS_EMPTY -> {
                context.getString(R.string.is_empty_nickname)
            }

            UsernameValidationResult.CORRECT -> {
                ""
            }
        }
    }
}

class PasswordValidator @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke(password: String): PasswordValidationResult {
        return if (password.length < 8) PasswordValidationResult.NOT_LONG_ENOUGH
        else if (password.count(Char::isUpperCase) == 0) PasswordValidationResult.NOT_ENOUGH_UPPERCASE
        else if (!password.contains("[0-9]".toRegex())) PasswordValidationResult.NOT_ENOUGH_DIGITS
        else PasswordValidationResult.CORRECT
    }

    fun getPasswordError(passwordValidationResult: PasswordValidationResult): String {
        return when (passwordValidationResult) {
            PasswordValidationResult.NOT_LONG_ENOUGH -> {
                context.getString(R.string.not_long_enough)
            }

            PasswordValidationResult.NOT_ENOUGH_DIGITS -> {
                context.getString(R.string.not_enough_digits)
            }

            PasswordValidationResult.NOT_ENOUGH_UPPERCASE -> {
                context.getString(R.string.not_enough_uppercase)
            }

            PasswordValidationResult.CORRECT -> {
                ""
            }
        }
    }
}

class EmailValidator @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke(email: String): EmailValidationResult {
        return if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EmailValidationResult.CORRECT
        } else EmailValidationResult.INCORRECT_FORMAT
    }

    fun getEmailError(emailValidationResult: EmailValidationResult): String {
        return when (emailValidationResult) {
            EmailValidationResult.INCORRECT_FORMAT -> {
                context.getString(R.string.incorrect_format_email)
            }

            EmailValidationResult.CORRECT -> {
                ""
            }
        }
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