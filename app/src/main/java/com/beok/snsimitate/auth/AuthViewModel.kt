package com.beok.snsimitate.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.common.model.AuthRequest
import com.beok.domain.auth.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _errMsg = MutableLiveData<String>()
    val errMsg: LiveData<String> get() = _errMsg

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
            val request = AuthRequest(nickname = nickName, introduction = introduce, pwd = password)
            if (isValidAuthRequest(request)) return@launch
            val result = authRepository.signUp(request).getOrNull()
            println()
        }

    private fun isValidAuthRequest(request: AuthRequest): Boolean {
        if (request.isNotValidNickname()) {
            _errMsg.value = "닉네임이 올바르지 않습니다."
            return true
        }
        if (request.isNotValidPassword()) {
            _errMsg.value = "패스워드가 올바르지 않습니다."
            return true
        }
        return false
    }

    private fun doSignIn(nickName: String, password: String) = viewModelScope.launch {
        val request = AuthRequest(nickname = nickName, pwd = password)
        if (isValidAuthRequest(request)) return@launch
        val result = authRepository.signIn(request).getOrNull()
        println()
    }
}
