package com.akendardi.cryptowallet.presentation.main.bottom_sheet_search

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.main.home_screen.SearchState

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
        placeholder = { Text(text = stringResource(R.string.search)) },
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