@file:OptIn(ExperimentalMaterialApi::class)

package com.shushant.common.compose.ui


import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.shushant.common.compose.theme.LocalBackgroundTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppBottomSheet(
    bottomSheetState: BottomSheetState = BottomSheetState.HalfExpanded,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit
) {
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )

    LaunchedEffect(key1 = bottomSheetState) {
        when (bottomSheetState) {
            BottomSheetState.Hidden -> modalSheetState.hide()
            BottomSheetState.HalfExpanded -> modalSheetState.show()
            BottomSheetState.Expanded -> modalSheetState.show()
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = LocalBackgroundTheme.current.color,
        sheetContent = sheetContent,
        content = content
    )
}

enum class BottomSheetState {
    Hidden, Expanded, HalfExpanded;

    companion object {
        fun BottomSheetState.toModalBottomSheetValue(): ModalBottomSheetValue {
            return when (this) {
                Hidden -> ModalBottomSheetValue.Hidden
                Expanded -> ModalBottomSheetValue.Expanded
                HalfExpanded -> ModalBottomSheetValue.HalfExpanded
            }
        }
    }
}

@Composable
fun rememberBottomSheetState(
    bottomSheetState: BottomSheetState = BottomSheetState.HalfExpanded
): MutableState<BottomSheetState> = remember { mutableStateOf(bottomSheetState) }