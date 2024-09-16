package com.akendardi.cryptowallet.presentation.splash

sealed class SplashState {

    data object Initial : SplashState()

    data class Success(val nextScreen: NextScreen) : SplashState()

    data object NetworkError : SplashState()
}

enum class NextScreen {
    Main, Login
}