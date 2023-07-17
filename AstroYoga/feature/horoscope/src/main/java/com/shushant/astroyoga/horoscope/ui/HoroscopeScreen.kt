package com.shushant.astroyoga.horoscope.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shushant.common.compose.theme.Dimens
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.utils.getGreetingMessage
import com.shushant.common.compose.utils.toDOB
import com.shushant.navigation.AppComposeNavigator
import com.shushant.resource.AppResource
import com.shushant.resource.getZodiacResource
import org.koin.androidx.compose.getViewModel

@Composable
fun HoroscopeScreen(
    composeNavigator: AppComposeNavigator, horoscopeViewModel: HoroscopeViewModel = getViewModel()
) {
    val lazyState = rememberLazyListState()
    val state by horoscopeViewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = AppResource.DASHBOARD.drawable),
            contentDescription = "dashboard",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .height(Dimens.imageHeight)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp)
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            state = lazyState
        ) {
            item {
                Text(
                    text = "Horoscope", style = Typography.bodyLarge.copy(
                        fontWeight = FontWeight.ExtraBold, color = Color.Black
                    )
                )
            }

            item {
                Text(
                    text = state.userResponse?.username?.getGreetingMessage() ?: "",
                    style = Typography.bodyLarge.copy(
                        fontWeight = FontWeight.Light, color = Color.Gray, fontSize = 26.sp
                    )
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(
                            id = horoscopeViewModel.getZodiacSign().getZodiacResource()
                        ),
                        contentDescription = horoscopeViewModel.getZodiacSign().uppercase(),
                        modifier = Modifier.size(24.dp),
                    )
                    Text(
                        text = horoscopeViewModel.getZodiacSign().uppercase(),
                        style = Typography.bodyLarge.copy(fontWeight = FontWeight.ExtraBold),
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }

            item {
                Text(
                    text = state.userResponse?.dob?.toDOB()?.uppercase() ?: "".plus("/ "),
                    style = Typography.bodyLarge.copy(fontWeight = FontWeight.ExtraBold),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}