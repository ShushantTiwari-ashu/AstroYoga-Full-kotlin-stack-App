package com.shushant.common.compose.utils

import androidx.compose.ui.unit.dp

/**
 * Convert a dimension to Ldpi(low-density) screens
 */
fun Float.toLdpi() = this.times(0.75).dp

/**
 * Convert a dimension to Hdpi(high-density) screens
 */
fun Float.toHdpi() = this.times(1.5).dp

/**
 * Convert a dimension to Xhdpi(extra-high-density) screens
 */
fun Float.toXhdpi() = this.times(2.0).dp
