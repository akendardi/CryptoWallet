package com.akendardi.cryptowallet.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.akendardi.cryptowallet.navigation.MainNavGraph
import com.akendardi.cryptowallet.navigation.Screen

@Composable
fun MainScreen(
    goToLogInScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        bottomBar = {
            if (currentDestination?.route != Screen.ProfileScreen.route) {
                BottomBar(navController)
            }

        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            MainNavGraph(
                navHostController = navController,
                goToLogInScreen = goToLogInScreen
            )
        }

    }

}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        Screen.BottomBarScreen.HomeScreenNavGraph,
        Screen.BottomBarScreen.SendScreen,
        Screen.BottomBarScreen.WalletScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        modifier = Modifier.height(100.dp),
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                navDestination = currentDestination,
                navController = navController
            )
        }
    }


}

@Composable
fun RowScope.AddItem(
    screen: Screen.BottomBarScreen,
    navDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        icon = {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = screen.icon, contentDescription = " NavBar Icon",
            )
        },
        selected = navDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onSurface,
            indicatorColor = Color.Transparent
        )
    )
}
