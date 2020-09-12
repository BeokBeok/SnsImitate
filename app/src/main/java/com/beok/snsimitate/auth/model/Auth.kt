package com.beok.snsimitate.auth.model

import com.beok.domain.auth.entity.AuthResponse

data class Auth(val userId: Int, val message: String)

fun AuthResponse.mapToVo() = Auth(userId = userId, message = error_msg ?: "")
