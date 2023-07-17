package com.shushant.astroyoga.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.shushant.chatiez.feature.auth.R
import com.shushant.common.compose.theme.Dimens
import com.shushant.common.compose.utils.textFieldColors
import com.shushant.common.compose.theme.PRIMARY500
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.theme.textColor
import com.shushant.common.compose.ui.GradientButton
import com.shushant.resource.AppResource
import com.shushant.resource.getPasswordToggle


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginFields(viewModel: LoginViewModel, loginState: LoginState) {
    val focusManager = LocalFocusManager.current
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = loginState.email,
        isError = !(loginState.isValidEmail ?: true),
        placeholder = {
            Text(
                text = "user@email.com",
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        },
        label = {
            Text(
                text = "Email or phone number",
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.None
            )
        },
        onValueChange = { viewModel.setEmail(it) },
        supportingText = {
            if (loginState.isValidEmail == false) {
                Text(text = "Incorrect email!", textAlign = TextAlign.Center)
            }
        },
        textStyle = Typography.bodyLarge.copy(textAlign = TextAlign.Start),
        leadingIcon = {
            Image(
                painter = painterResource(id = AppResource.EMAIL.drawable),
                contentDescription = "Email", colorFilter = ColorFilter.tint(
                    textColor
                )
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        ),
        colors = textFieldColors,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(), shape = RoundedCornerShape(10.dp)
    )

    OutlinedTextField(
        value = loginState.password,
        placeholder = { Text(text = "Password", textAlign = TextAlign.Center) },
        isError = !(loginState.isValidPassword ?: true),
        supportingText = {
            if (loginState.isValidPassword == false) {
                Text(text = "Incorrect password!", textAlign = TextAlign.Center)
            }
        },
        label = {
            Text(
                text = "Password",
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.None
            )
        },
        leadingIcon = {
            Image(
                painter = painterResource(id = AppResource.PASSWORD.drawable),
                contentDescription = "Email", colorFilter = ColorFilter.tint(
                    textColor
                )
            )
        },
        trailingIcon = {
            Image(
                painter = painterResource(id = passwordVisibility.getPasswordToggle().drawable),
                contentDescription = "password_toggle",
                modifier = Modifier.clickable { passwordVisibility = !passwordVisibility },
                colorFilter = ColorFilter.tint(
                    textColor
                )
            )
        },
        onValueChange = {
            viewModel.setPassword(it)
        },
        textStyle = Typography.bodyLarge.copy(textAlign = TextAlign.Start),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(
            '*'
        ),
        colors = textFieldColors,
        singleLine = true,
    )
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.forgot),
            textAlign = TextAlign.End,
            style = Typography.bodyLarge,
            color = PRIMARY500,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .wrapContentSize()
                .clickable { }
        )
    }
    GradientButton(
        buttonText = stringResource(R.string.log_in), height = Dimens.gradientButtonHeight
    ) {
        viewModel.login()
    }
}