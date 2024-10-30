package com.akendardi.cryptowallet.presentation.main.home_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.main.home_screen.SearchState
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetSearch(
    onDismiss: () -> Unit,
    searchState: SearchState,
    onQueryChanged: (String) -> Unit,
    startSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()

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
            .heightIn(1000.dp)
            .consumeWindowInsets(WindowInsets.ime)
            .padding(top = 100.dp),
    ) {
        Column {
            SearchTextField(
                searchState = searchState,
                onQueryChanged = onQueryChanged,
                startSearch = startSearch
            )
            LazyColumn {
                items(searchState.coins) { coin ->
                    SearchCoinItem(searchCoinInfo = coin)
                }
            }
        }

    }
}


@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    searchState: SearchState,
    onQueryChanged: (String) -> Unit,
    startSearch: () -> Unit
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(24)
            ),
        value = searchState.query,
        onValueChange = onQueryChanged,
        placeholder = { Text(text = "Поиск") },
        shape = RoundedCornerShape(24),
        keyboardActions = KeyboardActions(
            onDone = {
                startSearch()
            }
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.colors().copy(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    SearchTextField(
        searchState = SearchState(),
        onQueryChanged = {},
        startSearch = {}
    )
}