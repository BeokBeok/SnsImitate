package com.beok.snsimitate.card.paging

import androidx.paging.DataSource
import com.beok.domain.content.ContentDataSource
import com.beok.snsimitate.card.model.Card

class CardsDataSourceFactory(
    private val contentRepository: ContentDataSource
) : DataSource.Factory<Int, Card>() {

    override fun create(): DataSource<Int, Card> = CardsDataSource(contentRepository)
}
