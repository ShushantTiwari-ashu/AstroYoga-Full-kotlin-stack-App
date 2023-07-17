package com.shushant.common.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LinearProgressView(percentage: Float, lineColor: Color) {
    Box(modifier = Modifier) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.5.dp))
                .background(color = MaterialTheme.colorScheme.background)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(percentage)
                .height(4.dp)
                .clip(RoundedCornerShape(2.5.dp))
                .background(
                    color = lineColor
                )
        )
    }
}
