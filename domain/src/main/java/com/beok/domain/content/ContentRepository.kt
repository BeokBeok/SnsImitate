package com.beok.domain.content

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
}
