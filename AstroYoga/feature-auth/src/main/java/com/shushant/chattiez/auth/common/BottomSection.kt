package com.shushant.astroyoga.auth.common

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shushant.chatiez.feature.auth.R
import com.shushant.common.compose.ui.SignInOptions
import com.shushant.common.compose.ui.TextWithDivider

@Composable
fun BottomSection(googleSignIn: () -> Unit = {}) {
    TextWithDivider(text = stringResource(R.string.or))
    SignInOptions(
        modifier = Modifier.padding(top = 20.dp)
    ) {
        when (it) {
            SignInOptions.GITHUB -> {}
            SignInOptions.GOOGLE -> {
                googleSignIn.invoke()
            }
            SignInOptions.FACEBOOK -> {}
        }
    }
}