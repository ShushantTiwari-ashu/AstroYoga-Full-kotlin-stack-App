package com.shushant.astroyoga.network.client

import com.google.firebase.auth.FirebaseUser
import com.shushant.astroyoga.network.utils.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

typealias SignUpResponse = Either<Boolean>
typealias SendEmailVerificationResponse = Either<Boolean>
typealias SignInResponse = Either<Boolean>
typealias ReloadUserResponse = Either<Boolean>
typealias SendPasswordResetEmailResponse = Either<Boolean>
typealias RevokeAccessResponse = Either<Boolean>
typealias AuthStateResponse = StateFlow<Boolean>

interface AccountService {
    val currentUser: FirebaseUser?

    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String): SignUpResponse

    suspend fun sendEmailVerification(): SendEmailVerificationResponse

    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): SignInResponse

    suspend fun reloadFirebaseUser(): ReloadUserResponse

    suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse

    fun signOut()

    suspend fun revokeAccess(): RevokeAccessResponse

    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse
}