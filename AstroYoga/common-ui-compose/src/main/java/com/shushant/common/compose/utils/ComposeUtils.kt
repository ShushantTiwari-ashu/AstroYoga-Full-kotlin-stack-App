package com.shushant.common.compose.utils

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import com.shushant.common.compose.theme.CircleStrokeColor
import com.shushant.common.compose.theme.GradientEnd
import com.shushant.common.compose.theme.GradientStart
import com.shushant.common.compose.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
val textFieldColors
    @Composable
    get() = TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = CircleStrokeColor,
        focusedBorderColor = CircleStrokeColor,
        disabledBorderColor = CircleStrokeColor,
        textColor = textColor,
        disabledLabelColor = textColor,
        focusedLabelColor = textColor,
        errorLeadingIconColor = textColor,
        disabledLeadingIconColor = textColor,
        focusedLeadingIconColor = textColor,
        unfocusedLeadingIconColor = textColor,
        cursorColor = textColor
    )

val Float.brush: ShaderBrush
    @Composable
    get() = remember(this) {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                val widthOffset = size.width * this@brush
                val heightOffset = size.height * this@brush
                return LinearGradientShader(
                    colors = listOf(
                        GradientStart, GradientEnd
                    ),
                    from = Offset(widthOffset, heightOffset),
                    to = Offset(widthOffset + size.width, heightOffset + size.height),
                    tileMode = TileMode.Mirror
                )
            }
        }
    }

fun Modifier.noClickable() = then(Modifier.clickable(enabled = false) {})