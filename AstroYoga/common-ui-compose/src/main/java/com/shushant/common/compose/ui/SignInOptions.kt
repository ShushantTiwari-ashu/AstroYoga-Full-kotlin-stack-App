package com.shushant.common.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shushant.common.compose.theme.buttonColor
import com.shushant.common.compose.theme.textColor
import com.shushant.resource.AppResource

@Composable
fun SignInOptions(
    modifier: Modifier = Modifier,
    signInOptions: List<SignInOptions> = listOf(
        SignInOptions.GITHUB,
        SignInOptions.FACEBOOK,
        SignInOptions.GOOGLE
    ),
    onClick: (SignInOptions) -> Unit
) {
    Row(
        modifier = modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        signInOptions.forEach { item ->
            Image(painter = painterResource(id = item.icons.drawable),
                contentDescription = "google",
                modifier = Modifier
                    .clickable {
                        onClick.invoke(item)
                    }
                    .border(
                        width = 2.dp, color = buttonColor, shape = CircleShape
                    )
                    .size(48.dp),
                contentScale = ContentScale.Inside,
                colorFilter = ColorFilter.tint(
                    textColor
                ))
        }
    }
}

enum class SignInOptions(val icons: AppResource) {
    GOOGLE(AppResource.GOOGLE), FACEBOOK(AppResource.FACEBOOK), GITHUB(AppResource.GITHUB)
}