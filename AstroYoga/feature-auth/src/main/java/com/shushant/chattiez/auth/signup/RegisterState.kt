package com.shushant.astroyoga.auth.signup

import com.shushant.astroyoga.data.base.State

data class RegisterState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isValidUsername: Boolean? = null,
    val isValidPassword: Boolean? = null,
    val isValidConfirmPassword: Boolean? = null,
    val error: String? = null
) : State