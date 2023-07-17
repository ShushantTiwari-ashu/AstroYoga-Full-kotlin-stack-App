package com.shushant.astroyoga.onboard.filldetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.shushant.astroyoga.feature.onboard.R
import com.shushant.common.compose.theme.Dimens
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.ui.ChattiezButton
import com.shushant.resource.AppResource

@Composable
fun EnableReminderItem(
    userState: UserDetailsState, moveForward: () -> Unit
) {
    Box(modifier = Modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = 30.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                painter = painterResource(id = AppResource.ENABLE_REMINDER.drawable),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(Dimens.imageHeight)
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.horoscope_remiders),
                color = Color.Black,
                style = Typography.bodyLarge.copy(
                    fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Start
                ),
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Text(
                text = stringResource(R.string.remind_about_horoscope),
                color = Color.Gray,
                style = Typography.bodyLarge.copy(
                    fontSize = 18.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Start
                ),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp)
            )
        }
        ChattiezButton(
            buttonText = stringResource(R.string.enable_rem),
            enabled = userState.pob?.displayName.isNullOrEmpty().not(),
            modifier = Modifier
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter)
        ) {
            moveForward.invoke()
        }

        /*if (enablePermission) {
            AppPermission(content = {
                it.whatIfNotNullOrEmpty {
                    moveForward.invoke()
                } ?: run {
                    scope.launch {
                        snackBar.showSnackbar(
                            message = it,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            })
        }*/
    }
}
