package com.akendardi.cryptowallet.presentation.main.home_screen.components.title

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.akendardi.cryptowallet.R


@Composable
fun TitleTextHomeScreen(
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
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        color = MaterialTheme.colorScheme.onBackground
    )

}