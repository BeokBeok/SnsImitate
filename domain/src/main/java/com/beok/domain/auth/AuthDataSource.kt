package com.beok.domain.auth

import com.beok.common.model.AuthRequest
import com.beok.domain.auth.entity.AuthResponse

interface AuthDataSource {

    suspend fun signUp(authRequest: AuthRequest): Result<AuthResponse>

    suspend fun signIn(authRequest: AuthRequest): Result<AuthResponse>
}
