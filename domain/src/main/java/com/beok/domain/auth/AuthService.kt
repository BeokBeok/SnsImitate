package com.beok.domain.auth

import com.beok.common.model.AuthRequest
import com.beok.domain.auth.entity.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("users")
    suspend fun requestSignUp(@Body authRequest: AuthRequest): AuthResponse

    @POST("users/sign_in")
    suspend fun requestSignIn(@Body authRequest: AuthRequest): AuthResponse
}
