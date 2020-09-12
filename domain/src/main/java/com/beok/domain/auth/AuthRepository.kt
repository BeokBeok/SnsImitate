package com.beok.domain.auth

import com.beok.common.model.AuthRequest
import com.beok.domain.auth.entity.AuthResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService
) : AuthDataSource {

    override suspend fun signUp(authRequest: AuthRequest): Result<AuthResponse> =
        runCatching { authService.requestSignUp(authRequest) }

    override suspend fun signIn(authRequest: AuthRequest): Result<AuthResponse> =
        runCatching { authService.requestSignIn(authRequest) }
}
