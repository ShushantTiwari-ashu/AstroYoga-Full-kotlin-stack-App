package com.shushant.astroyoga.onboard.filldetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.rememberPagerState
import com.shushant.astroyoga.onboard.navigation.OnBoarding
import com.shushant.common.compose.ui.AppLoader
import com.shushant.common.compose.ui.BackPressHandler
import com.shushant.common.compose.ui.ChattiezLoadingIndicator
import com.shushant.common.compose.ui.carousel.CarouselView
import com.shushant.navigation.AppComposeNavigator
import com.shushant.navigation.Graph
import com.skydoves.whatif.whatIfNotNullOrEmpty
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun GetDetailsCarousel(
    composeNavigator: AppComposeNavigator, viewModel: UserDetailsViewModel = getViewModel()
) {
    val selectedIndex by viewModel.selectedIndex.collectAsStateWithLifecycle()
    val composable by viewModel.composableScreens.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(initialPage = selectedIndex)
    val scope = rememberCoroutineScope()
    val userState by viewModel.state.collectAsStateWithLifecycle()

    if (userState.success) {
        composeNavigator.navigateAndClearBackStack(
            OnBoarding.AnalyzeDetails.name,
            Graph.ROOT
        )
    }

    getCarouselItems(viewModel = viewModel, userState = userState, navigate = {
        scope.launch {
            viewModel.createUser()
        }
    }) {
        scope.launch {
            pagerState.animateScrollToPage(pagerState.currentPage + 1)
        }
    }.run {
        LaunchedEffect(key1 = Unit) {
            viewModel.addComposable(this@run)
        }
    }

    if (userState.loading) {
        AppLoader()
    }

    BackPressHandler(onBackPressed = { })

    composable.whatIfNotNullOrEmpty {
        CarouselView(
            onPageChange = {
                viewModel.setSelectedIndex(it)
            },
            onBack = {
                composeNavigator.pop()
            },
            pageSize = it.count(),
            pagerState = pagerState,
            showText = it.lastIndex == selectedIndex,
            scope = scope,
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) { page: Int ->
            val data = it[page].action
            if (data != null) {
                data()
            }
        }
    }
}
