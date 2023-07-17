package com.shushant.astroyoga.auth.activated

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.shushant.common.compose.theme.activatedDrawable
import com.shushant.navigation.AppComposeNavigator

@Composable
fun AccountActivated(composeNavigator: AppComposeNavigator) {
    Image(
        painter = painterResource(id = activatedDrawable),
        contentDescription = "",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxSize()
    )
}