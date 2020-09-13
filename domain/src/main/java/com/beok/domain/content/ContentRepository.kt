package com.beok.domain.content

import com.beok.domain.content.entity.CardDetailResponse
import com.beok.domain.content.entity.CardsResponse
import com.beok.domain.content.entity.HomeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val contentService: ContentService
) : ContentDataSource {

    private val ioDispatcher = Dispatchers.IO

    override suspend fun getHome(): Result<HomeResponse> = withContext(ioDispatcher) {
        runCatching { contentService.getHome() }
    }

    override suspend fun getCards(page: Int, perPage: Int): Result<CardsResponse> =
        withContext(ioDispatcher) {
            runCatching { contentService.getCards(page, perPage) }
        }

    override suspend fun getCardDetail(id: String): Result<CardDetailResponse> =
        withContext(ioDispatcher) {
            runCatching { contentService.getCardDetail(id) }
        }
}
