package com.beok.domain.auth

import com.beok.common.model.AuthRequest
import com.beok.domain.auth.entity.AuthResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService
) : AuthDataSource {

    private val ioDispatcher = Dispatchers.IO

    override suspend fun signUp(authRequest: AuthRequest): Result<AuthResponse> =
        withContext(ioDispatcher) {
            runCatching { authService.requestSignUp(authRequest) }
        }

    override suspend fun signIn(authRequest: AuthRequest): Result<AuthResponse> =
        withContext(ioDispatcher) {
            runCatching { authService.requestSignIn(authRequest) }
        }
}
