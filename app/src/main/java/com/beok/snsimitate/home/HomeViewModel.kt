package com.beok.snsimitate.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beok.common.base.BaseViewModel
import com.beok.common.ext.safeLaunch
import com.beok.common.model.ToastMessage
import com.beok.domain.content.ContentDataSource
import com.beok.snsimitate.R
import com.beok.snsimitate.home.model.Card
import com.beok.snsimitate.home.model.User
import com.beok.snsimitate.home.model.mapToVo

class HomeViewModel @ViewModelInject constructor(
    private val contentRepository: ContentDataSource
) : BaseViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> get() = _cards

    fun fetchHome() = viewModelScope.safeLaunch(coroutineExceptionHandler) {
        val result = contentRepository.getHome().getOrNull()?.mapToVo()
        if (result == null) {
            toastMessage.value =
                ToastMessage(isResource = true, message = R.string.msg_invalid_home_data.toString())
            return@safeLaunch
        }
        _users.value = result.popularUsers
        _cards.value = result.popularCards
    }
}
