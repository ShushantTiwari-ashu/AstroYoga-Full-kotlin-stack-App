package com.shushant.astroyoga.onboard.ui

import com.shushant.astroyoga.data.base.State
import com.shushant.resource.AppResource

data class SplashUiState(
    val background: AppResource = AppResource.UNKNOWN,
    val logo: AppResource = AppResource.UNKNOWN,
    val hasUserData: Boolean = false
) : State