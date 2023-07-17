package com.shushant.common.compose.utils

import android.graphics.Color
import android.graphics.Typeface
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.core.text.getSpans

class LinkStringHandler : LinkMovementMethod() {
    fun getTextWithClickableString(
        fullText: String,
        hyperLinkText: String,
        url: String,
        onLinkClick: (String) -> Unit
    ): CharSequence? {
        val linkTextStartIndex = fullText.indexOf(hyperLinkText)
        val linkTextEndIndex = linkTextStartIndex + hyperLinkText.length

        if (fullText.isNotEmpty() && linkTextStartIndex > 0 && linkTextEndIndex > 0) {
            val msp = SpannableString(fullText)
            // set styles for all chars
            msp.setSpan(RelativeSizeSpan(0.86f), 0, fullText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            msp.setSpan(ForegroundColorSpan(Color.BLACK), 0, fullText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            msp.setSpan(StyleSpan(Typeface.NORMAL), 0, fullText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            // for clickable link text
            val clickableSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    onLinkClick(url)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }

            // make link texts not follow styles of non-link texts
            msp.removeSpan(msp.getSpans<String>(linkTextStartIndex, linkTextEndIndex))

            // attach clickable link span
            msp.setSpan(clickableSpan, linkTextStartIndex, linkTextEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            return msp
        }

        return null
    }
}
