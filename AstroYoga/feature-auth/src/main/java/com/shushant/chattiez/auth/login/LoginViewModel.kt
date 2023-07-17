package com.shushant.astroyoga.auth.login

import androidx.lifecycle.viewModelScope
import com.shushant.astroyoga.data.base.BaseViewModel
import com.shushant.astroyoga.network.client.AccountService
import com.shushant.common.compose.ui.AuthValidator
import kotlinx.coroutines.launch

class LoginViewModel(
    private val accountService: AccountService,
) : BaseViewModel<LoginState>(LoginState()) {

    fun setEmail(username: String) {
        setState { state -> state.copy(email = username) }
    }

    fun setPassword(password: String) {
        setState { state -> state.copy(password = password) }
    }

    fun login() {
        if (!validateCredentials()) return

        viewModelScope.launch {
            val email = currentState.email
            val password = currentState.password

            setState { state -> state.copy(isLoading = true) }

            val response = accountService.firebaseSignInWithEmailAndPassword(email, password)

            response.onSuccess { result ->
                setState { state ->
                    state.copy(
                        isLoading = false, isLoggedIn = result, error = null
                    )
                }
            }.onFailure { message ->
                setState { state ->
                    state.copy(
                        isLoading = false, isLoggedIn = false, error = message
                    )
                }
            }
        }
    }

    fun clearError() = setState { state -> state.copy(error = null) }

    private fun validateCredentials(): Boolean {
        val isValidEmail = AuthValidator.isValidEmail(currentState.email)
        val isValidPassword = AuthValidator.isValidPassword(currentState.password)

        setState { state ->
            state.copy(
                isValidEmail = isValidEmail, isValidPassword = isValidPassword
            )
        }

        return isValidEmail && isValidPassword
    }
}