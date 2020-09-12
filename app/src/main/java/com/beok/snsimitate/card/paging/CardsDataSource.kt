package com.beok.snsimitate.card.paging

import androidx.paging.PageKeyedDataSource
import com.beok.domain.content.ContentDataSource
import com.beok.domain.content.entity.CardsItem
import com.beok.snsimitate.card.model.Card
import com.beok.snsimitate.card.model.mapToVo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardsDataSource(
    private val contentRepository: ContentDataSource
) : PageKeyedDataSource<Int, Card>() {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Card>
    ) {
        ioScope.launch {
            val result = contentRepository.getCards().getOrNull()?.cards?.map(CardsItem::mapToVo)
                ?: emptyList()
            callback.onResult(result, null, 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Card>) = Unit

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Card>) {
        ioScope.launch {
            val result =
                contentRepository.getCards(page = params.key + 1)
                    .getOrNull()?.cards?.map(CardsItem::mapToVo) ?: emptyList()
            callback.onResult(result, params.key + 1)
        }
    }

    companion object {
        private const val PER_PAGE = 20
    }
}
