package com.shushant.astroyoga.onboard.filldetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.shushant.astroyoga.feature.onboard.R
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.ui.ChattiezButton
import com.shushant.resource.AppResource

@Composable
fun UserNameItem(userName: (String) -> Unit, userState: UserDetailsState, moveForward: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 30.dp)
    ) {
        Image(
            painter = painterResource(id = AppResource.TELL_YOUR_NAME.drawable),
            contentDescription = "",
            contentScale = ContentScale.Inside,
        )
        Text(
            text = "In order to build your birth chart,",
            color = Color.Gray,
            style = Typography.bodyLarge.copy(fontSize = 18.sp),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
        )

        Text(
            text = "What's your name?",
            color = Color.Black,
            style = Typography.bodyLarge.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Start
            ),
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        TextField(
            value = userState.userName,
            onValueChange = { userName.invoke(it) },
            textStyle = Typography.bodyLarge.copy(fontSize = 28.sp, color = Color.Black),
            keyboardOptions = KeyboardOptions(KeyboardCapitalization.Words),
            placeholder = {
                Text(
                    text = "Type Your Name",
                    style = Typography.bodyLarge.copy(
                        fontSize = 28.sp,
                        color = Color.LightGray,
                        textAlign = TextAlign.Start
                    )
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(40.dp))
        ChattiezButton(
            buttonText = stringResource(R.string.continue_text),
            enabled = userState.userName.isNotEmpty()
        ) {
            moveForward.invoke()
        }
    }
}