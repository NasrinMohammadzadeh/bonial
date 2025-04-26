package com.example.bonialchallenge.feature.ui

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.bonialchallenge.ui.theme.LightGrey
import com.example.bonialchallenge.ui.theme.ProgressBackground
import com.example.bonialchallenge.ui.theme.White

@Composable
fun LoadingScreen(modifier: Modifier) {
    Column(
        modifier = modifier
            .background(White)
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        Row(modifier = Modifier) {
            ShimmerCard(Modifier.weight(1f))
            Spacer(modifier = Modifier.size(10.dp))
            ShimmerCard(Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.size(10.dp))
        ShimmerCard(Modifier)
        Spacer(modifier = Modifier.size(10.dp))
        Row {
            ShimmerCard(Modifier.weight(1f))
            Spacer(modifier = Modifier.size(10.dp))
            ShimmerCard(Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.size(10.dp))
        ShimmerCard(Modifier.weight(1f))
    }
}


@Composable
private fun ShimmerCard(modifier: Modifier) {
    Card(
        modifier = modifier
            .background(White, shape = RectangleShape)
    ) {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .shimmer()
        )

    }
}


fun Modifier.shimmer(): Modifier = composed {

    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "transition")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1500)
        ), label = "shimmerAnimation"
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                ProgressBackground,
                LightGrey,
                ProgressBackground
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}