package com.shushant.astroyoga.onboard.filldetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults

import com.shushant.astroyoga.feature.onboard.R
import com.shushant.common.compose.theme.Dimens
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.ui.ChattiezButton
import com.shushant.resource.AppResource
import java.time.LocalTime

@Composable
fun SelectorTOBItem(
    tob: (String) -> Unit,
    userState: UserDetailsState,
    moveForward: () -> Unit
) {
    val width = LocalConfiguration.current.screenWidthDp
    Box(modifier = Modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = 30.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = AppResource.SELECT_DOB.drawable),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(Dimens.imageHeight)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(R.string.dob_title),
                color = Color.Gray,
                style = Typography.bodyLarge.copy(fontSize = 18.sp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp)
            )

            Text(
                text = stringResource(R.string.select_tob),
                color = Color.Black,
                style = Typography.bodyLarge.copy(
                    fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Start
                ),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            WheelTimePicker(
                maxTime = LocalTime.now(),
                textStyle = Typography.bodyLarge.copy(color = Color.Black),
                textColor = Color.Black,
                size = DpSize(width = width.dp, height = 200.dp),
                timeFormat = TimeFormat.AM_PM,
                startTime = if (userState.tob.isNotEmpty()) LocalTime.parse(userState.tob) else LocalTime.now(),
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                selectorProperties = WheelPickerDefaults.selectorProperties(
                    color = Color.Transparent,
                    border = BorderStroke(2.dp, Color.Gray)
                )
            ) { snappedDate -> tob.invoke(snappedDate.toString()) }

        }

        ChattiezButton(
            buttonText = stringResource(R.string.continue_text),
            enabled = userState.tob.isNotEmpty(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        ) {
            moveForward.invoke()
        }
    }
}