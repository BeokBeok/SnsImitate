package com.beok.snsimitate.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.common.model.AuthRequest
import com.beok.domain.auth.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var isLogin = false
        private set

    fun setupLoginMode(isLogin: Boolean) {
        this.isLogin = isLogin
    }

    fun doAuth(nickName: String, password: String, introduce: String) {
        if (isLogin) doSignIn(nickName, password) else doSignUp(nickName, password, introduce)
    }

    private fun doSignUp(nickName: String, password: String, introduce: String) =
        viewModelScope.launch {
            val result = authRepository.signUp(
                AuthRequest(
                    nickname = nickName,
                    introduction = introduce,
                    pwd = password
                )
            ).getOrNull()
            println()
        }

    private fun doSignIn(nickName: String, password: String) = viewModelScope.launch {
        val result =
            authRepository.signIn(AuthRequest(nickname = nickName, pwd = password)).getOrNull()
        println()
    }
}
