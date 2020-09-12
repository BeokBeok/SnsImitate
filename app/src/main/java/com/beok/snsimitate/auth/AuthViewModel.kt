package com.beok.snsimitate.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beok.common.base.BaseViewModel
import com.beok.common.ext.safeLaunch
import com.beok.common.model.AuthRequest
import com.beok.common.model.ToastMessage
import com.beok.domain.auth.AuthDataSource
import com.beok.snsimitate.R
import com.beok.snsimitate.auth.model.Auth
import com.beok.snsimitate.auth.model.mapToVo

class AuthViewModel @ViewModelInject constructor(
    private val authRepository: AuthDataSource
) : BaseViewModel() {

    private val _isSuccessLogin = MutableLiveData<Boolean>()
    val isSuccessLogin: LiveData<Boolean> get() = _isSuccessLogin

    var isLogin = false
        private set

    fun setupLoginMode(isLogin: Boolean) {
        this.isLogin = isLogin
    }

    fun doAuth(nickName: String, password: String, introduce: String) {
        if (isLogin) doSignIn(nickName, password) else doSignUp(nickName, password, introduce)
    }

    private fun doSignUp(nickName: String, password: String, introduce: String) =
        viewModelScope.safeLaunch(coroutineExceptionHandler) {
            val request = AuthRequest(nickname = nickName, introduction = introduce, pwd = password)
            if (isValidAuthRequest(request)) return@safeLaunch
            val result = authRepository.signUp(request).getOrNull()?.mapToVo()
            if (checkErrorMessageIfExist(result, R.string.msg_sign_up_failed)) return@safeLaunch
            doSignIn(nickName, password)
        }

    private fun doSignIn(nickName: String, password: String) =
        viewModelScope.safeLaunch(coroutineExceptionHandler) {
            val request = AuthRequest(nickname = nickName, pwd = password)
            if (isValidAuthRequest(request)) return@safeLaunch
            val result = authRepository.signIn(request).getOrNull()?.mapToVo()
            if (checkErrorMessageIfExist(result, R.string.msg_sign_in_failed)) return@safeLaunch
            _isSuccessLogin.value = true
        }

    private fun checkErrorMessageIfExist(result: Auth?, defaultErrMessageResource: Int): Boolean {
        if (result == null) {
            toastMessage.value =
                ToastMessage(isResource = true, message = defaultErrMessageResource.toString())
            return true
        }
        if (result.message.isNotEmpty()) {
            toastMessage.value = ToastMessage(message = result.message)
            return true
        }
        return false
    }

    private fun isValidAuthRequest(request: AuthRequest): Boolean {
        if (request.isNotValidNickname()) {
            toastMessage.value =
                ToastMessage(
                    isResource = true,
                    message = R.string.msg_invalid_nickname.toString()
                )
            return true
        }
        if (request.isNotValidPassword()) {
            toastMessage.value =
                ToastMessage(
                    isResource = true,
                    message = R.string.msg_invalid_password.toString()
                )
            return true
        }
        return false
    }
}
