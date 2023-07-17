package com.shushant.common.compose.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.theme.textColor
import com.shushant.resource.AppResource

@Composable
fun ChattiezToastDialog(
    icon: AppResource,
    message: String
) {
    FullScreenDialog {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Row(
                    modifier = Modifier.padding(
                        start = 28.dp,
                        top = 20.dp,
                        end = 28.dp,
                        bottom = 100.dp
                    )
                ) {
                    Icon(painter = painterResource(id = icon.drawable), contentDescription = "")
                    Text(
                        text = message,
                        style = Typography.bodyMedium,
                        color = textColor,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            }
        }
    }
}
