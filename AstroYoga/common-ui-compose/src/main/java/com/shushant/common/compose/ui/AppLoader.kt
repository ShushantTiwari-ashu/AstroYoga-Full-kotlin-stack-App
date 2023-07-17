package com.shushant.common.compose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun AppLoader() {
    val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://assets8.lottiefiles.com/private_files/lf30_l8csvun7.json"))
    LottieAnimation(
        composition = lottieComposition,
        modifier = Modifier
            .fillMaxSize()
            .shadow(
                elevation = 4.dp,
                ambientColor = Color.Transparent,
                spotColor = Color.Transparent
            )
    )
}