package com.beok.domain.content

import com.beok.domain.content.entity.CardsResponse
import com.beok.domain.content.entity.HomeResponse

interface ContentDataSource {

    suspend fun getHome(): Result<HomeResponse>

    suspend fun getCards(): Result<CardsResponse>
}
