package com.beok.snsimitate.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beok.common.base.BaseViewModel
import com.beok.common.ext.safeLaunch
import com.beok.common.model.ToastMessage
import com.beok.domain.content.ContentDataSource
import com.beok.snsimitate.R
import com.beok.snsimitate.card.model.Card
import com.beok.snsimitate.detail.model.mapToVo
import com.beok.snsimitate.home.model.User

class DetailViewModel @ViewModelInject constructor(
    private val contentRepository: ContentDataSource
) : BaseViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _card = MutableLiveData<Card>()
    val card: LiveData<Card> get() = _card

    private val _recommendCards = MutableLiveData<List<Card>>()
    val recommendCards: LiveData<List<Card>> get() = _recommendCards

    fun fetchCardDetail(id: String) = viewModelScope.safeLaunch(coroutineExceptionHandler) {
        val result = contentRepository.getCardDetail(id).getOrNull()?.mapToVo()
        if (result == null) {
            toastMessage.value =
                ToastMessage(isResource = true, message = R.string.msg_invalid_card_data.toString())
            return@safeLaunch
        }
        _user.value = result.user
        _card.value = result.card
        _recommendCards.value = result.recommendCards
    }
}
