package com.akendardi.cryptowallet.presentation.auth.hello_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.presentation.auth.auth_screen.Title

private const val INFO = "Начните торговать криптовалютой без лишних усилий.\n" +
        "Покупайте, продавайте или конвертируйте свою криптовалюту одним кликом!"

@Composable
fun HelloScreen(
    onButtonClick: () -> Unit
) {

    HelloScreenContent(
        onButtonClick = onButtonClick
    )
}

@Composable
fun HelloScreenContent(onButtonClick: () -> Unit) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Title()
            AnimatedImageAndText(onButtonClick = onButtonClick)
        }

    }
}

@Composable
fun AnimatedImageAndText(onButtonClick: () -> Unit) {
    var visible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        visible = true
    }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(2000))
    ) {
        Column {
            HelloImage()
            HeadText()
            InfoText()
            ButtonGoNext(
                onButtonClick = onButtonClick
            )
        }

    }
}

@Composable
fun HelloImage() {
    Image(
        bitmap = ImageBitmap.imageResource(R.drawable.hello_screen),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth()
    )

}

@Composable
fun HeadText() {
    Column(
        modifier = Modifier.padding(
            start = 20.dp,
            bottom = 10.dp
        )
    ) {
        Text(
            text = "Добро пожаловать",
            textAlign = TextAlign.Start,
            fontSize = 30.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "в Crypto Wallet",
            textAlign = TextAlign.Start,
            fontSize = 40.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }

}

@Composable
fun InfoText() {
    Text(
        text = INFO,
        textAlign = TextAlign.Start,
        fontSize = 15.sp,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(
                PaddingValues(
                    start = 20.dp
                )
            ),
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun ButtonGoNext(onButtonClick: () -> Unit) {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Button(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            onClick = { onButtonClick() },
        ) {
            Text(
                text = "Понятно",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

