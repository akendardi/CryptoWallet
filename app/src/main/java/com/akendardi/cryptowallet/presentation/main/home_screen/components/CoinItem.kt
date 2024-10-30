package com.akendardi.cryptowallet.presentation.main.home_screen.components

import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.akendardi.cryptowallet.domain.entity.CoinInfo
import com.akendardi.cryptowallet.presentation.theme.NegativeDifferenceColor
import com.akendardi.cryptowallet.presentation.theme.PositiveDifferenceColor


@Composable
fun CoinItem(
    coinInfo: CoinInfo,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .height(80.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            CoinItemImage(
                imageUrl = coinInfo.imageUrl
            )


            NameAndSymbol(
                name = coinInfo.name,
                symbol = coinInfo.symbol
            )

            PriceGraph(
                dataPoints = coinInfo.priceInfo,
                color = getDifferenceColor(coinInfo.todayDifference),
                modifier = Modifier.weight(0.8f)
            )

            DifferenceAndPr(
                coinInfo = coinInfo
            )

        }
    }

}


@Composable
fun DifferenceAndPr(
    coinInfo: CoinInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = coinInfo.getFormattedDifference(),
            color = getDifferenceColor(coinInfo.todayDifference),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Text(
            text = coinInfo.getFormattedPrice(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

fun getDifferenceColor(todayDifference: Double): Color {
    return if (todayDifference > 0) {
        PositiveDifferenceColor
    } else {
        NegativeDifferenceColor
    }
}

@Composable
fun CoinItemImage(
    imageUrl: Uri,
    modifier: Modifier = Modifier
) {

    Image(
        modifier = modifier
            .clip(CircleShape)
            .size(58.dp),
        painter = rememberAsyncImagePainter(
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build()
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun RowScope.NameAndSymbol(
    modifier: Modifier = Modifier,
    name: String,
    symbol: String
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .weight(0.8f),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 18.sp
        )
        Text(
            text = symbol,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PriceGraph(
    dataPoints: List<Double>,
    color: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.fillMaxHeight()) {
        if (dataPoints.isEmpty()) return@Canvas

        // Находим минимальные и максимальные значения для нормализации
        val minY = dataPoints.minOrNull() ?: 0.0
        val maxY = dataPoints.maxOrNull() ?: 1.0
        val yRange = maxY - minY

        val normalizedDataPoints =
            dataPoints.map { size.height - ((it - minY) / yRange * size.height) }

        val spacing = size.width / (dataPoints.size - 1)

        // Создаем путь для рисования кривой
        val path = Path().apply {
            moveTo(0f, normalizedDataPoints[0].toFloat())
            for (i in 0 until normalizedDataPoints.size - 1) {
                val startX = i * spacing
                val startY = normalizedDataPoints[i].toFloat()
                val endX = (i + 1) * spacing
                val endY = normalizedDataPoints[i + 1].toFloat()

                // Вычисляем контрольные точки для кривой Безье
                val controlX = (startX + endX) / 2
                val controlY = (startY + endY) / 2

                // Добавляем кривую в путь

                // Добавляем кривую в путь
                quadraticTo(controlX, controlY, endX, endY)
            }
        }

        // Рисуем путь
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = 2.dp.toPx())
        )
    }
}


@Preview
@Composable
private fun CoinItemPreview() {
    CoinItem(
        coinInfo = CoinInfo(
            id = 1803,
            name = "Ethereum",
            symbol = "BTC",
            price = 20000.0,
            todayDifference = 5.15,
            imageUrl = Uri.parse(
                "https://s2.coinmarketcap.com/static/img/coins/64x64/1.png"
            ),
            priceInfo = emptyList()
        )
    )
}