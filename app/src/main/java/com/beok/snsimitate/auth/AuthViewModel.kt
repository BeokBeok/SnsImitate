package com.beok.snsimitate.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.common.model.AuthRequest
import com.beok.common.model.ToastMessage
import com.beok.domain.auth.AuthRepository
import com.beok.snsimitate.R
import com.beok.snsimitate.auth.model.Auth
import com.beok.snsimitate.auth.model.mapToVo
import kotlinx.coroutines.launch

class AuthViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _toastMsg = MutableLiveData<ToastMessage>()
    val toastMsg: LiveData<ToastMessage> get() = _toastMsg

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
        viewModelScope.launch {
            val request = AuthRequest(nickname = nickName, introduction = introduce, pwd = password)
            if (isValidAuthRequest(request)) return@launch
            val result = authRepository.signUp(request).getOrNull()?.mapToVo()
            if (checkErrorMessageIfExist(result, R.string.msg_sign_up_failed)) return@launch
            doSignIn(nickName, password)
        }

    private fun doSignIn(nickName: String, password: String) = viewModelScope.launch {
        val request = AuthRequest(nickname = nickName, pwd = password)
        if (isValidAuthRequest(request)) return@launch
        val result = authRepository.signIn(request).getOrNull()?.mapToVo()
        if (checkErrorMessageIfExist(result, R.string.msg_sign_in_failed)) return@launch
        _isSuccessLogin.value = true
    }

    private fun checkErrorMessageIfExist(result: Auth?, defaultErrMessageResource: Int): Boolean {
        if (result == null) {
            _toastMsg.value =
                ToastMessage(isResource = true, message = defaultErrMessageResource.toString())
            return true
        }
        if (result.message.isNotEmpty()) {
            _toastMsg.value = ToastMessage(isResource = false, message = result.message)
            return true
        }
        return false
    }

    private fun isValidAuthRequest(request: AuthRequest): Boolean {
        if (request.isNotValidNickname()) {
            _toastMsg.value =
                ToastMessage(
                    isResource = true,
                    message = R.string.msg_invalidate_nickname.toString()
                )
            return true
        }
        if (request.isNotValidPassword()) {
            _toastMsg.value =
                ToastMessage(
                    isResource = true,
                    message = R.string.msg_invalidate_password.toString()
                )
            return true
        }
        return false
    }
}
