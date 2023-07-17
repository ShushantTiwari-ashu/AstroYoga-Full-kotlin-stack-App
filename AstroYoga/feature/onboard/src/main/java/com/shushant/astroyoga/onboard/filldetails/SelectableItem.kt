package com.shushant.astroyoga.onboard.filldetails

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shushant.common.compose.theme.Typography

@Composable
fun SelectableItem(selected: Boolean, text: String, onClick: (String) -> Unit) {
    TextButton(
        onClick = {
            onClick.invoke(text)
        },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(20.dp)
            .border(width = 2.dp, shape = RoundedCornerShape(10.dp), color = if (selected) Color.Black else Color.Gray)
            .fillMaxWidth()
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = text,
                style = Typography.bodyLarge,
                color = Color.Gray,
                textAlign = TextAlign.Start, modifier = Modifier.padding(start = 10.dp)
            )
            if (selected) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        }
    }
}