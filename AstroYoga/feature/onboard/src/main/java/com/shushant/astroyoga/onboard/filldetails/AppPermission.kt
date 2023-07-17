package com.shushant.astroyoga.onboard.filldetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import dev.shreyaspatil.permissionflow.compose.rememberMultiplePermissionState

@Composable
fun AppPermission(content: (String) -> Unit) {
    val permissionsState by rememberMultiplePermissionState(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.RECORD_AUDIO,
    )

    if (permissionsState.allGranted) {
        // Render something
        content.invoke("")
    }

    val grantedPermissions = permissionsState.grantedPermissions

    // Do something with `grantedPermissions`

    val deniedPermissions = permissionsState.deniedPermissions

    // Do something with `deniedPermissions`
}