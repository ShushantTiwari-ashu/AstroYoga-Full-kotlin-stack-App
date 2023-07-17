package com.shushant.common.compose.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import kotlin.math.pow
import kotlin.math.sqrt

fun Modifier.textBrush(brush: Brush) = this
    .graphicsLayer(alpha = 0.99f)
    .drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(brush, blendMode = BlendMode.SrcAtop)
        }
    }

fun Modifier.testTagAndContentDescription(tagAndDescription: String): Modifier =
    testTag(tagAndDescription)
        .semantics {
            contentDescription = tagAndDescription
            // We can add testTagsAsResourceId = true, after compose updated to 1.2.x
        }

fun Modifier.thenIf(modifier: Modifier, predict: () -> Boolean) =
    if (predict()) {
        this.then(modifier)
    } else {
        this
    }

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}


/**
 * A draw modifier that draws a dashed bottom line
 */
fun Modifier.drawDashedLineBottom(
    lineWidth: Dp,
    color: Color
): Modifier =
    drawLineBottom(
        lineWidth,
        color,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 4f), 0f)
    )

/**
 * A draw modifier that draws a solid bottom line
 */
internal fun Modifier.drawSolidLineBottom(
    lineWidth: Dp,
    color: Color
): Modifier =
    drawLineBottom(
        lineWidth,
        color
    )

/**
 * A draw modifier that draws a TOP line
 */
fun Modifier.drawLineTop(
    lineWidth: Dp,
    color: Color,
    pathEffect: PathEffect? = null
): Modifier {
    return drawBehind {
        val strokeWidth = lineWidth.value * density
        val y = 0 - strokeWidth / 2
        drawLine(
            color,
            Offset(0f, y),
            Offset(size.width, y),
            strokeWidth,
            pathEffect = pathEffect
        )
    }
}

/**
 * A draw modifier that draws a bottom line
 */
fun Modifier.drawLineBottom(
    lineWidth: Dp,
    color: Color,
    pathEffect: PathEffect? = null
): Modifier {
    return drawBehind {
        val strokeWidth = lineWidth.value * density
        val y = size.height - strokeWidth / 2
        drawLine(
            color,
            Offset(0f, y),
            Offset(size.width, y),
            strokeWidth,
            pathEffect = pathEffect
        )
    }
}

fun Modifier.clickOutsideClearFocus(focusManager: FocusManager, enable: Boolean = true) =
    if (enable) {
        pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    focusManager.clearFocus()
                }
            )
        }
    } else this

/**
 * Create a modifier for gradient background with colors and angle
 * @property colors list of colors
 * @property angle of inclination of a straight line
 */
fun Modifier.gradientBackground(colors: List<Color>, angle: Float) = this.then(
    Modifier.drawBehind {
        val angleRad = angle / 180f * Math.PI
        val x = kotlin.math.cos(angleRad).toFloat() // Fractional x
        val y = kotlin.math.sin(angleRad).toFloat() // Fractional y

        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(x * radius, y * radius)

        val exactOffset = Offset(
            x = offset.x.coerceAtLeast(0f).coerceAtMost(size.width),
            y = size.height - Math.min(offset.y.coerceAtLeast(0f), size.height)
        )
        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ),
            size = size
        )
    }
)