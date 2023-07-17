package com.shushant.astroyoga.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shushant.chatiez.feature.auth.R
import com.shushant.astroyoga.auth.common.BottomSection
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.theme.snackbarHostState
import com.shushant.common.compose.theme.textColor
import com.shushant.common.compose.ui.ChattiezClickableText
import com.shushant.common.compose.ui.DialogBoxLoading
import com.shushant.common.compose.utils.toast
import com.shushant.navigation.AppComposeNavigator
import com.shushant.navigation.ChattiezScreens
import com.shushant.resource.AppResource
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(
    composeNavigator: AppComposeNavigator,
    loginViewModel: LoginViewModel = getViewModel(),
) {
    val context = LocalContext.current
    val loginUiState by loginViewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = snackbarHostState

    LaunchedEffect(loginUiState.isLoggedIn) {
        if (loginUiState.isLoggedIn) {
            composeNavigator.navigateAndClearBackStack(ChattiezScreens.OnBoarding.route)
        }
    }

    if (loginUiState.isLoading) {
        DialogBoxLoading()
    }

    if (loginUiState.error != null) {
        LaunchedEffect(Unit) {
           snackbarHostState.currentSnackbarData?.dismiss()
           snackbarHostState.showSnackbar(
                message = loginUiState.error ?: "",
                duration = SnackbarDuration.Long
            )
            loginViewModel.clearError()
        }
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(top = 70.dp, start = 20.dp, end = 20.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Image(
            painter = painterResource(id = AppResource.LOGO.drawable),
            contentDescription = stringResource(
                R.string.logo
            ),
            modifier = Modifier.size(96.dp)
        )
        Text(
            text = stringResource(R.string.log_in_to_make_your_memories),
            style = Typography.bodyLarge,
            color = textColor
        )
        LoginFields(loginViewModel, loginState = loginUiState)
        ChattiezClickableText {
            context.toast(message = it)
        }
        BottomSection()
    }
}