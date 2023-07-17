package com.shushant.astroyoga.auth.login

import com.shushant.astroyoga.data.base.State

data class LoginState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val error: String? = null,
    val email: String = "",
    val password: String = "",
    val isValidEmail: Boolean? = null,
    val isValidPassword: Boolean? = null
) : State