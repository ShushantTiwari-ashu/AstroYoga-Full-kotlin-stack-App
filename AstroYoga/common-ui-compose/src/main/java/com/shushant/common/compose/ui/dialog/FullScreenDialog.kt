package com.shushant.common.compose.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun FullScreenDialog(
    dialogProperties: DialogProperties = DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false,
    ),
    onDismiss: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val currentContent by rememberUpdatedState(content)
    Dialog({ onDismiss.invoke() }, properties = dialogProperties) {
        currentContent()
    }
}
