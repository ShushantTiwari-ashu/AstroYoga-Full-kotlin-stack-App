package com.shushant.astroyoga.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.shushant.common.compose.theme.AstroNavigationColorDefaults
import com.shushant.common.compose.theme.Typography
import com.shushant.navigation.destinations.BottomNavItem

@Composable
fun AstroBottomBar(
    destinations: List<BottomNavItem>,
    navigate: (BottomNavItem) -> Unit,
    currentDestination: NavDestination?
) {

    val bottomBarDestination = destinations.any { it.screen_route == currentDestination?.route }

    if (bottomBarDestination) {
        NavigationBar(
            containerColor = Color.Transparent,
            contentColor = AstroNavigationColorDefaults.navigationContentColor(),
            modifier = Modifier
                .safeDrawingPadding()
                .padding(start = 16.dp, end = 16.dp, bottom = 5.dp, top = 5.dp)
                .background(shape = RoundedCornerShape(10.dp), color = Color.White)
        ) {
            destinations.forEach { destination ->
                val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
                NavigationBarItem(icon = {
                    Icon(
                        painterResource(id = destination.icon),
                        contentDescription = destination.title
                    )
                },
                    label = {
                        Text(
                            text = destination.title, fontSize = 9.sp, style = Typography.bodyLarge
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AstroNavigationColorDefaults.navigationSelectedItemColor(),
                        unselectedIconColor = AstroNavigationColorDefaults.navigationContentColor(),
                        selectedTextColor = AstroNavigationColorDefaults.navigationSelectedItemColor(),
                        unselectedTextColor = AstroNavigationColorDefaults.navigationContentColor(),
                        indicatorColor = AstroNavigationColorDefaults.navigationIndicatorColor()
                    ),
                    selected = currentDestination?.hierarchy?.any {
                        it.route == destination.screen_route
                    } == true,
                    enabled = true,
                    onClick = {
                        navigate.invoke(destination)
                    })
            }
        }
    }
}


private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: BottomNavItem) =
    this?.hierarchy?.any {
        it.route?.contains(destination.screen_route, true) ?: false
    } ?: false