package com.beok.domain.content

import com.beok.domain.content.entity.CardsResponse
import com.beok.domain.content.entity.HomeResponse

interface ContentDataSource {

    suspend fun getHome(): Result<HomeResponse>

    suspend fun getCards(page: Int = 1, perPage: Int = 20): Result<CardsResponse>
}
