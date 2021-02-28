package com.darkos.kmp.androidApp

import com.darkos.kmp.common.auth.AuthManager
import com.darkos.kmp.common.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GoogleAuthManager : AuthManager {

    private val auth: FirebaseAuth by lazy { Firebase.auth }

    override suspend fun signOut() {
        auth.signOut()
    }

    override suspend fun hasAuthorizedUser(): Boolean {
        return auth.currentUser != null
    }

    override suspend fun signInByEmail(email: String, password: String): AuthResult {
        return try {
            suspendCoroutine {
                runSignInBeEmail(email, password, it)
            }
        } catch (e: Exception) {
            AuthResult.Error(e)
        }
    }

    private fun runSignInBeEmail(
        email: String,
        password: String,
        continuation: Continuation<AuthResult>
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    continuation.resume(AuthResult.Success)
                } else {
                    continuation.resume(
                        AuthResult.Error(
                            it.exception ?: RuntimeException("auth error")
                        )
                    )
                }
            }
            .addOnCanceledListener {
                continuation.resume(AuthResult.Canceled)
            }
            .addOnFailureListener {
                continuation.resume(AuthResult.Error(it))
            }
    }
}