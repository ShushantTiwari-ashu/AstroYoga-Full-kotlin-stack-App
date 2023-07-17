package com.shushant.astroyoga.network.client

import com.google.firebase.auth.FirebaseAuth
import com.shushant.astroyoga.network.utils.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class AccountServiceImpl(private val auth: FirebaseAuth) : AccountService {
    override val currentUser get() = auth.currentUser

    override suspend fun firebaseSignUpWithEmailAndPassword(
        email: String, password: String
    ): SignUpResponse {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Either.Success(true)
        } catch (e: Exception) {
            Either.Error(e.message ?: "Unknown Error!")
        }
    }

    override suspend fun sendEmailVerification(): SendEmailVerificationResponse {
        return try {
            auth.currentUser?.sendEmailVerification()?.await()
            Either.Success(true)
        } catch (e: Exception) {
            Either.Error(e.message ?: "Unknown Error!")
        }
    }

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String, password: String
    ): SignInResponse {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Either.Success(true)
        } catch (e: Exception) {
            Either.Error(e.message ?: "Unknown Error!")
        }
    }

    override suspend fun reloadFirebaseUser(): ReloadUserResponse {
        return try {
            auth.currentUser?.reload()?.await()
            Either.Success(true)
        } catch (e: Exception) {
            Either.Error(e.message ?: "Unknown Error!")
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Either.Success(true)
        } catch (e: Exception) {
            Either.Error(e.message ?: "Unknown Error!")
        }
    }

    override fun signOut() = auth.signOut()

    override suspend fun revokeAccess(): RevokeAccessResponse {
        return try {
            auth.currentUser?.delete()?.await()
            Either.Success(true)
        } catch (e: Exception) {
            Either.Error(e.message ?: "Unknown Error!")
        }
    }

    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), auth.currentUser == null)
}