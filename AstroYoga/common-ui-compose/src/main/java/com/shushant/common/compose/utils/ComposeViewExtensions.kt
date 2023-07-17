package com.shushant.common.compose.utils

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.platform.LocalView

@Composable
fun rememberViewInterpolNestedScrollConnection(
    view: View = LocalView.current
): NestedScrollConnection {
    return remember(view) {
        ViewInterpolNestedScrollConnection(view)
    }
}
