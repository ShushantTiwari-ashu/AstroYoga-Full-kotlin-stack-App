package com.shushant.common.compose.utils

import androidx.compose.runtime.compositionLocalOf

val LocalOnFinishDispatcher = compositionLocalOf<(() -> Unit)?> { null }