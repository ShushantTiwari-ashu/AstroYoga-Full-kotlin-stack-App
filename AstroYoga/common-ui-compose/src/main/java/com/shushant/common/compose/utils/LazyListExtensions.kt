package com.shushant.common.compose.utils

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.getVisibleItems(): List<Int> {
    return if (layoutInfo.visibleItemsInfo.isEmpty()) {
        emptyList()
    } else {
        val fullyVisibleItemsInfo = layoutInfo.visibleItemsInfo.toMutableList()
        val lastItem = fullyVisibleItemsInfo.last()
        val viewportWidth = layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset

        if (lastItem.offset + lastItem.size > viewportWidth) {
            fullyVisibleItemsInfo.removeLast()
        }

        val firstItemIfLeft = fullyVisibleItemsInfo.firstOrNull()
        if (firstItemIfLeft != null && firstItemIfLeft.offset < layoutInfo.viewportStartOffset) {
            fullyVisibleItemsInfo.removeFirst()
        }
        fullyVisibleItemsInfo.map { it.index }
    }
}
