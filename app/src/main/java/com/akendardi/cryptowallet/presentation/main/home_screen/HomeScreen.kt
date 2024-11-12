package com.akendardi.cryptowallet.presentation.main.home_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.presentation.main.bottom_sheet_search.BottomSheetSearch

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    HomeScreenContent(
        state = HomeScreenUIState(),
        onProfileClickListener = {},
        logout = {},
        onSearchButtonClick = {},
        loadNextPage = {},
        onItemClicked = {},
        onRefresh = {}
    )
}


@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onProfileClickListener: () -> Unit,
    logout: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    HomeScreenContent(
        state = state,
        onProfileClickListener = {
            onProfileClickListener()
        },
        logout = {
            viewModel.logOut()
            logout()
        },
        onSearchButtonClick = viewModel::launchSearchMode,
        loadNextPage = viewModel::loadCoins,
        onItemClicked = {},
        onRefresh = {
            viewModel.startRefresh()
        }
    )

    if (state.screenMode == ScreenMode.SEARCH) {
        BottomSheetSearch(
            onDismiss = viewModel::exitSearchMode,
            onQueryChanged = viewModel::onSearchQueryChange,
            searchState = state.searchState,
            startSearch = viewModel::startSearch,
            onItemClicked = {}
        )
    }
}



