package com.akendardi.cryptowallet.presentation.main.home_screen.components.bottom_sheet_search

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.akendardi.cryptowallet.presentation.main.home_screen.SearchState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetSearch(
    onDismiss: () -> Unit,
    searchState: SearchState,
    onQueryChanged: (String) -> Unit,
    startSearch: () -> Unit,
    onItemClicked: (String) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()

    BottomSheetSearchContent(
        sheetState = sheetState,
        searchState = searchState,
        onQueryChanged = onQueryChanged,
        startSearch = startSearch,
        scope = scope,
        onDismiss = onDismiss,
        onItemClicked = onItemClicked,
    )
}

