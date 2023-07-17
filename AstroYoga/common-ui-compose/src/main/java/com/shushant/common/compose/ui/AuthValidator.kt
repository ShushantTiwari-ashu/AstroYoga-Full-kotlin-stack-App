package com.shushant.common.compose.ui

object AuthValidator {
    fun isValidEmail(email: String): Boolean =
        email.isNotBlank() || email.isNotEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isValidPassword(password: String): Boolean = password.trim().length in (8..50)

    fun isPasswordAndConfirmPasswordSame(
        password: String,
        confirmedPassword: String
    ): Boolean = password.trim() == confirmedPassword.trim()
}