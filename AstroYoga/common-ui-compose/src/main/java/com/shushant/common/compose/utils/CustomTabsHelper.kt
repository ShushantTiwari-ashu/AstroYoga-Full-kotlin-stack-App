package com.shushant.common.compose.utils

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

object CustomTabsHelper {

    /**
     * Opens the URL on a Custom Tab if possible.
     *
     * @param uri the Uri to be opened.
     */
    fun openCustomTab(context: Context, uri: Uri) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent: CustomTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, uri)
    }
}
