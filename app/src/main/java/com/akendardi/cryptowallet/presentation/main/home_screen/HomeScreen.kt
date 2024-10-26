package com.akendardi.cryptowallet.presentation.main.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.domain.entity.UserInfo
import com.akendardi.cryptowallet.presentation.theme.PositiveDifferenceColor
import com.akendardi.cryptowallet.presentation.theme.profileGradientColor

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onProfileClickListener: () -> Unit,
    logout: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    HomeScreenContent(
        state,
        onProfileClickListener = {
            onProfileClickListener()
        },
        logout = {
            viewModel.logOut()
            logout()
        }
    )
}

@Composable
fun TitleTextHomeScreen(
    userInfoState: UserInfo,
    onProfileClickListener: () -> Unit,
    modifier: Modifier = Modifier,
    logout: () -> Unit
) {
    val uri = userInfoState.profileUri.toString()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onProfileClickListener()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(58.dp),
            painter = rememberAsyncImagePainter(
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(uri)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build()
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Greeting(userInfoState.userName)

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = {
                logout()
            }
        ) {
            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null)
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    val text = buildAnnotatedString {
        append(stringResource(R.string.hello_comma))
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append(name)
        }
    }

    Text(
        modifier = modifier,
        text = text,
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

}

@Preview
@Composable
fun UserBalanceInfo(
//    totalBalance: String,
//    differencePercent: String,
//    onDepositButtonClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(

        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(profileGradientColor)
                .padding(12.dp)
        ) {
            Text(
                text = "Баланс",
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "83,745.12$",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                style = MaterialTheme.typography.titleLarge
            )


            Text(
                text = "+10.2% (+327$)",
                color = PositiveDifferenceColor,
                style = MaterialTheme.typography.titleMedium
            )


            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color.Black.copy(alpha = 0.3f))
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BalanceButton("Пополнить")
                BalanceButton("Вывести")
            }
        }

    }
}

@Composable
fun RowScope.BalanceButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(10.dp),
        shape = RoundedCornerShape(15),
        onClick = {}
    ) {
        Text(text = text, color = MaterialTheme.colorScheme.onBackground)
    }
}