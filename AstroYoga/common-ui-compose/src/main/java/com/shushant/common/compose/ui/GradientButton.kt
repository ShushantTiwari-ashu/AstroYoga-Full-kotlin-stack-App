package com.shushant.common.compose.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.shushant.common.compose.utils.brush
import com.shushant.common.compose.theme.LIGHT_COLOR
import com.shushant.common.compose.theme.Typography
import com.shushant.resource.AppResource

@Composable
fun GradientButton(
    buttonText: String,
    icon: AppResource? = null,
    height: Dp? = null,
    onClick: () -> Unit,
) {
    val infiniteTransition = rememberInfiniteTransition()

    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .background(
                brush = offset.brush, shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
            .then(height?.let { Modifier.height(it) } ?: Modifier.wrapContentHeight())
            .clickable { onClick.invoke() }
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Image(
                    painter = painterResource(id = it.drawable),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(color = LIGHT_COLOR)
                )
                Spacer(modifier = Modifier.width(20.dp))
            }
            Text(
                text = buttonText,
                style = Typography.bodyLarge,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}