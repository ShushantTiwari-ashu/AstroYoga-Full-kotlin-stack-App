package com.shushant.astroyoga.onboard.filldetails.horoscopeanalyzer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shushant.common.compose.theme.Dimens
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.theme.snackbarHostState
import com.shushant.navigation.AppComposeNavigator
import com.shushant.navigation.Graph
import com.shushant.resource.AppResource
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun AnalyzeHoroscopeItem(
    composeNavigator: AppComposeNavigator, viewModel: HoroscopeAnalyzerViewModel = getViewModel()
) {
    val horoscopeState by viewModel.state.collectAsStateWithLifecycle()
    val snackbar = snackbarHostState
    val scope = rememberCoroutineScope()
    var progressValue by remember {
        mutableStateOf(0.01)
    }

    if (horoscopeState.success != null) {
        progressValue = 1.0
        composeNavigator.navigateAndClearBackStack(Graph.HOME, popUpto = Graph.ONBOARDING)
    }

    if (horoscopeState.error.isNotEmpty()) {
        LaunchedEffect(key1 = horoscopeState.error) {
            scope.launch {
                snackbar.showSnackbar(message = horoscopeState.error)
            }
            repeat(99) {
                progressValue += 0.01f
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 30.dp)
    ) {
        Image(
            painter = painterResource(id = AppResource.ANALYZE_SCOPE.drawable),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(Dimens.imageHeight.plus(50.dp))
                .fillMaxWidth(),
            colorFilter = ColorFilter.tint(Color.Black)
        )

        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Analyzing your horoscope",
                color = Color.Black,
                style = Typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic
                ),
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Text(
                text = "18%", color = Color.Black, style = Typography.bodyLarge.copy(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                ), modifier = Modifier.padding(horizontal = 20.dp)
            )

            LinearProgressIndicator(
                progress = progressValue.toFloat(),
                color = Color.Black,
                trackColor = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}