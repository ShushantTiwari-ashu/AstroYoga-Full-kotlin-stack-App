package com.shushant.common.compose.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

fun Context.toSetting() {
    val myAppSettings = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.parse("package:" + this.packageName)
    )
    myAppSettings.addCategory(Intent.CATEGORY_DEFAULT)
    myAppSettings.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    this.startActivity(myAppSettings)
}