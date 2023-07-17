package com.shushant.common.compose.ui

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.shushant.common.compose.theme.PRIMARY500
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.theme.textColor

@Composable
fun ChattiezClickableText(click: (String) -> Unit) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color  = textColor)) {
            append("Don't have an account? ")
        }
        pushStringAnnotation(tag = "account", annotation = "Don't have an account? ")
        withStyle(style = SpanStyle(color = PRIMARY500)) {
            append("Sign up")
        }
        pop()
    }
    ClickableText(text = annotatedString, style = Typography.bodyLarge, onClick = { offset ->
        annotatedString.getStringAnnotations(tag = "account", start = offset, end = offset)
            .firstOrNull()?.let {
                click.invoke(it.item)
            }
    })
}