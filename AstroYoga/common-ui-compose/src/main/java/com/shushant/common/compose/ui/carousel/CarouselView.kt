package com.shushant.common.compose.ui.carousel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.shushant.common.compose.theme.Typography
import com.shushant.astroyoga.common.compose.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

const val carouselView = "carouselView"

/**
 * A layout composable that has [HorizontalPager] with carousel animation
 * @property modifier Composable that accept a [Modifier] as a parameter to be applied to the whole component
 * @property onPageChange callback to get the pageChange
 * @property pageSize number of pages
 * @property selectedPage setCurrentItem of the pager
 * @property content the children composable to be laid out.
 */
@Composable
fun CarouselView(
    modifier: Modifier,
    onPageChange: (Int) -> Unit,
    onBack: () -> Unit,
    pageSize: Int,
    pagerState: PagerState,
    showText: Boolean = false,
    scope: CoroutineScope,
    content: @Composable (page: Int) -> Unit,
) {
    LaunchedEffect(key1 = pagerState) {
        // Collect from the pager state a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.distinctUntilChanged().collect { page ->
            delay(50)
            onPageChange.invoke(page)
        }
    }

    Box(modifier = modifier) {
        HorizontalPager(
            count = pageSize,
            contentPadding = PaddingValues(
                horizontal = 0.dp, vertical = 0.dp,
            ),
            modifier = modifier.semantics { this.contentDescription = carouselView },
            state = pagerState,
            userScrollEnabled = false
        ) { page ->
            content(page)
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(18.dp),
        )

        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 10.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .clickable {
                    scope.launch {
                        if (pagerState.currentPage > 0) {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        } else {
                            onBack.invoke()
                        }
                    }
                },
        )

        if (showText) {
            Text(
                text = stringResource(R.string.dont_have_hand),
                style = Typography.bodyLarge.copy(color = Color.Black, fontSize = 10.sp),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 14.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .clickable {

                    }
            )
        }
    }
}
