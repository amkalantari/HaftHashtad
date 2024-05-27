package com.hafthashtad.android.core.designsystem.component

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieImage(modifier: Modifier, @RawRes image: Int,iterations : Int = 1) {

    val composition = rememberLottieComposition(
        LottieCompositionSpec.RawRes(image)
    )

    LottieAnimation(
        composition = composition.value,
        modifier = modifier,
        iterations = iterations,
        alignment = Alignment.TopCenter,
        restartOnPlay = true,
        contentScale = ContentScale.Inside
    )
}