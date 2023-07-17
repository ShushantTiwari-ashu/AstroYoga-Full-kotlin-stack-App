package com.shushant.common.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommonSnackBar(snackbarHostState: SnackbarHostState) {
    SnackbarHost(
        snackbarHostState,
    ) {
        Snackbar(
            modifier = Modifier
                .navigationBarsPadding()
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(10.dp)
                ),
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.background,
            actionContentColor = MaterialTheme.colorScheme.background
        ) {
            GradientButton(buttonText = it.visuals.message) {
                /*if (snackbarHostState.currentSnackbarData?.visuals?.duration != SnackbarDuration.Indefinite) {
                    snackbarHostState.currentSnackbarData?.dismiss()
                }*/
            }
        }
    }
}
