/*
 * Copyright (c) 2023 Shushant Tiwari.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.shushant.common.compose.utils

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.runtime.staticCompositionLocalOf

@OptIn(ExperimentalMaterialApi::class)
class MainPanel constructor(
    private val swappableState: SwipeableState<Int>
) {
    val isExpanded: Boolean get() = swappableState.currentValue != 0

    suspend fun expand() {
        swappableState.animateTo(1)
    }

    suspend fun collapse() {
        swappableState.animateTo(0)
    }
}

val LocalPanel = staticCompositionLocalOf<MainPanel> {
    error("No LocalPanel given")
}