package com.beok.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beok.common.model.ToastMessage
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel : ViewModel() {

    protected val toastMessage = MutableLiveData<ToastMessage>()
    val toastMsg: LiveData<ToastMessage> get() = toastMessage

    protected val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        toastMessage.value = ToastMessage(message = throwable.message ?: "")
    }
}
