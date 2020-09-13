package com.beok.snsimitate.card

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.beok.common.base.BaseViewModel
import com.beok.domain.content.ContentDataSource
import com.beok.snsimitate.card.model.Card
import com.beok.snsimitate.card.paging.CardsDataSourceFactory

class CardsViewModel @ViewModelInject constructor(
    contentRepository: ContentDataSource
) : BaseViewModel() {

    val cards: LiveData<PagedList<Card>> = CardsDataSourceFactory(contentRepository)
        .toLiveData(config = Config(pageSize = PER_PAGE))

    private val _selectedCard = MutableLiveData<Card>()
    val selectedCard: LiveData<Card> get() = _selectedCard

    fun onClick(item: Card) {
        _selectedCard.value = item
    }

    companion object {
        private const val PER_PAGE = 20
    }
}