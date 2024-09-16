package com.akendardi.cryptowallet.navigation

sealed class Screen(val route: String) {
    data object SplashScreen: Screen(ROUTE_SPLASH_SCREEN)

    data object AuthGraph: Screen(ROUTE_AUTH_GRAPH)
    data object LoginScreen: Screen(ROUTE_LOGIN_SCREEN)
    data object CreateAccountScreen: Screen(ROUTE_CREATE_ACCOUNT)
    data object ResetPasswordScreen: Screen(ROUTE_RESET_PASSWORD)


    data object MainScreenNavGraph: Screen(ROUTE_MAIN_SCREEN_NAV_GRAPH)
    data object MainScreen: Screen(ROUTE_MAIN_SCREEN)

    private companion object{
        const val ROUTE_SPLASH_SCREEN = "splashScreen"

        const val ROUTE_AUTH_GRAPH= "authNavGraph"
        const val ROUTE_LOGIN_SCREEN = "login"
        const val ROUTE_CREATE_ACCOUNT = "createAccount"
        const val ROUTE_RESET_PASSWORD = "resetPassword"

        const val ROUTE_MAIN_SCREEN_NAV_GRAPH = "mainScreenNavGraph"
        const val ROUTE_MAIN_SCREEN = "mainScreen"
    }
}