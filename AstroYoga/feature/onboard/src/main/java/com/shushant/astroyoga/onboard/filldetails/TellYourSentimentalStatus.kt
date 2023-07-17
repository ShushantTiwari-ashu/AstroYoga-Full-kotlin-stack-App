package com.shushant.astroyoga.onboard.filldetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

import com.shushant.astroyoga.feature.onboard.R
import com.shushant.common.compose.theme.Dimens
import com.shushant.common.compose.theme.NAME_COLOR
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.ui.ChattiezButton
import com.shushant.resource.AppResource

@Composable
fun TellYourSentimentalStatusItem(
    updateSentiment: (SentimentalStatus) -> Unit,
    userState: UserDetailsState,
    moveForward: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = AppResource.SENTIMENTAL_STATUS.drawable),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(Dimens.imageHeight)
                .fillMaxWidth()
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = NAME_COLOR)) {
                    append(userState.userName.removeSuffix(" "))
                }
                append(stringResource(R.string.pick_sentiments))
            },
            color = Color.Gray,
            style = Typography.bodyLarge.copy(fontSize = 18.sp),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp)
        )

        Text(
            text = stringResource(R.string.select_status),
            color = Color.Black,
            style = Typography.bodyLarge.copy(
                fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        sentimentalStatus.forEach {
            SelectableItem(selected = userState.sentimentalStatus == it, text = it.name) {
                updateSentiment.invoke(SentimentalStatus.valueOf(it))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.more_than),
            textAlign = TextAlign.Center,
            style = Typography.bodyLarge.copy(color = Color.LightGray, fontSize = 12.sp),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        )
        ChattiezButton(
            buttonText = stringResource(R.string.continue_text),
            enabled = userState.sentimentalStatus != SentimentalStatus.UNKNOWN
        ) {
            moveForward.invoke()
        }
    }
}