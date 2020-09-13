package com.beok.domain.content

import com.beok.domain.content.entity.CardDetailResponse
import com.beok.domain.content.entity.CardsResponse
import com.beok.domain.content.entity.HomeResponse
import com.beok.domain.content.entity.UserResponse

interface ContentDataSource {

    suspend fun getHome(): Result<HomeResponse>

    suspend fun getCards(page: Int = 1, perPage: Int = 20): Result<CardsResponse>

    suspend fun getCardDetail(id: String): Result<CardDetailResponse>

    suspend fun getUserDetail(id: String): Result<UserResponse>
}
