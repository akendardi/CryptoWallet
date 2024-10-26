package com.akendardi.cryptowallet.presentation.main.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreenContent(
    state: HomeScreenUIState,
    onProfileClickListener: () -> Unit,
    logout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TitleTextHomeScreen(
            userInfoState = state.userInfoState,
            onProfileClickListener = {
                onProfileClickListener()
            },
            logout = logout
        )

        UserBalanceInfo()

    }
}