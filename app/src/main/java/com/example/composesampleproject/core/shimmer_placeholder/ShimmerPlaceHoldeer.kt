package com.example.composesampleproject.core.shimmer_placeholder

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerPlaceHolder(height: Int){

        val transition = rememberInfiniteTransition()
        val colorShimmer = transition.animateColor(
            initialValue = Color.LightGray,
            targetValue = Color.DarkGray,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 5.dp , vertical = 5.dp)
                .fillMaxWidth()
                .height(height = height.dp)
                .background(color = colorShimmer.value)
        )

}