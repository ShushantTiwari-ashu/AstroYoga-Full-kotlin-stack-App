package com.shushant.astroyoga.onboard.filldetails.pob

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shushant.astroyoga.data.model.LocationSearchResultItem
import com.shushant.common.compose.theme.LIGHT_COLOR_ASTRO
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.ui.BackPressHandler
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun BottomSheetLayout(
    data: List<LocationSearchResultItem>, onClick: (LocationSearchResultItem) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { false },
        skipHalfExpanded = false
    )
    val keyboardController = LocalSoftwareKeyboardController.current
    BackPressHandler {
        if (modalSheetState.isVisible) {
            coroutineScope.launch { modalSheetState.hide() }
        }
    }

    LaunchedEffect(data) {
        coroutineScope.launch {
            keyboardController?.hide()
            modalSheetState.show()
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState, scrimColor = Transparent, sheetShape = RoundedCornerShape(
            topStart = 12.dp, topEnd = 12.dp,
        ), sheetContent = {

            LazyColumn {
                items(data) {
                    Text(text = it.displayName.toString(),
                        style = Typography.bodyLarge.copy(fontSize = 18.sp),
                        color = Color.DarkGray,
                        modifier = Modifier
                            .clickable {
                                onClick.invoke(it)
                                coroutineScope.launch { modalSheetState.hide() }
                            }
                            .padding(20.dp))
                }
            }
        }, sheetBackgroundColor = Color.White, sheetContentColor = LIGHT_COLOR_ASTRO
    ) {

    }
}