package com.akendardi.cryptowallet.presentation.main.bottom_sheet_search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.main.home_screen.SearchState
import com.akendardi.cryptowallet.presentation.main.home_screen.components.coin_item.SearchCoinItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetSearchContent(
    sheetState: SheetState,
    searchState: SearchState,
    onQueryChanged: (String) -> Unit,
    startSearch: () -> Unit,
    scope: CoroutineScope,
    onDismiss: () -> Unit,
    onItemClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            scope.launch {
                sheetState.hide()
                onDismiss()
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 100.dp)
    ) {
        Column{
            SearchTextField(
                searchState = searchState,
                onQueryChanged = onQueryChanged,
                startSearch = startSearch
            )
            LazyColumn {
                items(searchState.coins) { coin ->
                    SearchCoinItem(
                        searchCoinInfo = coin,
                        onItemClicked = onItemClicked
                    )
                }
            }
        }

    }
}