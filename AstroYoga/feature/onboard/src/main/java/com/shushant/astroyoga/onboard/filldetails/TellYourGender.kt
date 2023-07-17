package com.shushant.astroyoga.onboard.filldetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.shushant.astroyoga.feature.onboard.R
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shushant.common.compose.theme.Dimens
import com.shushant.common.compose.theme.NAME_COLOR
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.ui.ChattiezButton
import com.shushant.resource.AppResource

@Composable
fun TellYourGenderItem(
    updateGender: (Gender) -> Unit, userState: UserDetailsState, moveForward: () -> Unit
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = 30.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = AppResource.GENDER.drawable),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(Dimens.imageHeight)
                    .fillMaxWidth()
            )
            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.ok))
                    withStyle(style = SpanStyle(color = NAME_COLOR)) {
                        append(userState.userName.removeSuffix(" "))
                    }
                    append(stringResource(R.string.tell_your_gender))
                },
                color = Color.Gray,
                style = Typography.bodyLarge.copy(fontSize = 18.sp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp)
            )

            Text(
                text = stringResource(R.string.select_your_gender),
                color = Color.Black,
                style = Typography.bodyLarge.copy(
                    fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Start
                ),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            SelectableItem(selected = userState.gender == Gender.MALE, text = Gender.MALE.name) {
                updateGender.invoke(Gender.valueOf(it))
            }
            SelectableItem(
                selected = userState.gender == Gender.FEMALE,
                text = Gender.FEMALE.name
            ) {
                updateGender.invoke(Gender.valueOf(it))
            }
            SelectableItem(
                selected = userState.gender == Gender.NON_BINARY, text = Gender.NON_BINARY.name
            ) {
                updateGender.invoke(Gender.valueOf(it))
            }
        }

        ChattiezButton(
            buttonText = stringResource(R.string.continue_text),
            enabled = userState.gender != Gender.UNKNOWN,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            moveForward.invoke()
        }
    }
}