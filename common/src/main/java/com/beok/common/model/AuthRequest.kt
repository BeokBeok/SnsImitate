package com.beok.common.model

data class AuthRequest(val nickname: String, val introduction: String? = null, val pwd: String) {

    fun isNotValidNickname() = nickname.isEmpty()

    fun isNotValidPassword() = pwd.isEmpty()
}
