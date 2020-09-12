package com.beok.domain.content

import com.beok.domain.content.entity.CardsResponse
import com.beok.domain.content.entity.HomeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ContentService {

    @GET("home")
    suspend fun getHome(): HomeResponse

    @GET("cards")
    suspend fun getCards(
        @Query("page") page: Int,
        @Query("per") perPage: Int
    ): CardsResponse
}
