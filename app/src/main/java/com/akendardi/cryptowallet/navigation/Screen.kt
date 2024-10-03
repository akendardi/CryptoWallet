package com.akendardi.cryptowallet.navigation

sealed class Screen(val route: String) {
    data object SplashScreen: Screen(ROUTE_SPLASH_SCREEN)

    data object AuthScreen: Screen(ROUTE_AUTH_GRAPH)

    data object HelloScreen: Screen(ROUTE_HELLO_SCREEN)

    data object MainScreenNavGraph: Screen(ROUTE_MAIN_SCREEN_NAV_GRAPH)
    data object MainScreen: Screen(ROUTE_MAIN_SCREEN)

    private companion object{
        const val ROUTE_SPLASH_SCREEN = "splashScreen"

        const val ROUTE_AUTH_GRAPH= "authScreen"

        const val ROUTE_HELLO_SCREEN= "helloScreen"

        const val ROUTE_MAIN_SCREEN_NAV_GRAPH = "mainScreenNavGraph"
        const val ROUTE_MAIN_SCREEN = "mainScreen"
    }
}