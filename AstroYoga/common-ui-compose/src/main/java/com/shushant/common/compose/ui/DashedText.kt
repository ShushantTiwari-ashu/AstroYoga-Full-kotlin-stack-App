package com.shushant.common.compose.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun DashedText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    color: Color,
    dashedLineWith: Float = 1f,
    dashedLineHeight: Float = 1f
) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashedLineWith, dashedLineHeight), 0f)
    Column(modifier = Modifier.width(IntrinsicSize.Max)) {
        Text(
            modifier = modifier,
            text = text,
            style = style.copy(color = color)
        )
        Canvas(
            Modifier
                .fillMaxWidth()
                .height(dashedLineHeight.dp)
        ) {
            drawLine(
                color = color,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                pathEffect = pathEffect
            )
        }
    }
}
