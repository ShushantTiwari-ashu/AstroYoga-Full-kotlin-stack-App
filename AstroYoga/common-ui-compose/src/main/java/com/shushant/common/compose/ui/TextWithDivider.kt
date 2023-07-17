package com.shushant.common.compose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.theme.buttonColor
import com.shushant.common.compose.theme.textColor

@Composable
fun TextWithDivider(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Divider(
            color = buttonColor,
            thickness = 2.dp,
            modifier = Modifier.weight(4f)
        )
        Text(
            text = text,
            style = Typography.bodyLarge,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Divider(
            color = buttonColor,
            thickness = 2.dp,
            modifier = Modifier.weight(4f)
        )
    }
}