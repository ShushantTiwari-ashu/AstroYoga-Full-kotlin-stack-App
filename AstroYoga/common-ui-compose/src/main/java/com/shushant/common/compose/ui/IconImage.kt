package com.shushant.common.compose.ui

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder

@Composable
fun IconImage(url: String = "", drawableRes: Drawable? = null,modifier: Modifier = Modifier) {
    val data = drawableRes ?: url
    Image(
        painter = rememberImagePainter(
            data = data,
            builder = {
                if (url.endsWith(".svg")) decoderFactory { result, options, _ ->
                    SvgDecoder(
                        result.source,
                        options
                    )
                }
            }
        ),
        modifier = modifier,
        contentDescription = "",
    )
}