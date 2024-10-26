package com.akendardi.cryptowallet.presentation.main.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TopButtons(
    onButtonBackClick: () -> Unit,
    onEditPhotoClicked: () -> Unit
) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        IconButton(onClick = {
            onButtonBackClick()
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null
            )
        }
        IconButton(onClick = {
            onEditPhotoClicked()
        }) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null
            )
        }
    }
}