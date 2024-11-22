package com.akendardi.cryptowallet.presentation.main.home_screen.components.title

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akendardi.cryptowallet.presentation.main.home_screen.UserInfoState

@Composable
fun TitleHomeScreen(
    generalUserInfoState: UserInfoState,
    onProfileClickListener: () -> Unit,
    modifier: Modifier = Modifier,
    logout: () -> Unit
) {
    val uri = generalUserInfoState.profileUri.toString()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onProfileClickListener()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ProfileImage(
            modifier = Modifier.size(50.dp),
            uri = uri
        )

        TitleTextHomeScreen(
            generalUserInfoState.userName
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = {
                logout()
            }
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null)
        }
    }
}


