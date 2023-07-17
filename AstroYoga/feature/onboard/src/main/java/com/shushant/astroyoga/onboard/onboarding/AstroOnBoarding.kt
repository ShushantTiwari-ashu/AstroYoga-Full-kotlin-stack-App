package com.shushant.astroyoga.onboard.onboarding

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
import com.shushant.astroyoga.onboard.navigation.OnBoarding
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.ui.ChattiezButton
import com.shushant.navigation.AppComposeNavigator
import com.shushant.resource.AppResource

@Composable
fun AstroOnBoarding(composeNavigator: AppComposeNavigator) {
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Image(
            painter = painterResource(id = AppResource.ASTRO_ON_BOARDING.drawable),
            contentDescription = "onboarding", contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.future),
            style = Typography.bodyLarge.copy(fontSize = 28.sp, fontWeight = FontWeight.W800),
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 20.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.daily_horoscope),
            style = Typography.bodyLarge.copy(fontSize = 16.sp),
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 20.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        ChattiezButton(buttonText = stringResource(R.string.get_started)) {
            composeNavigator.navigate(OnBoarding.TakeUserDetails.name)
        }
    }
}