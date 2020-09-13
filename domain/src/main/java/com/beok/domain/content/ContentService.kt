package com.beok.domain.content

import com.beok.domain.content.entity.CardDetailResponse
import com.beok.domain.content.entity.CardsResponse
import com.beok.domain.content.entity.HomeResponse
import com.beok.domain.content.entity.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentService {

    @GET("home")
    suspend fun getHome(): HomeResponse

    @GET("cards")
    suspend fun getCards(
        @Query("page") page: Int,
        @Query("per") perPage: Int
    ): CardsResponse

    @GET("cards/{id}")
    suspend fun getCardDetail(@Path("id") id: String): CardDetailResponse

    @GET("users/{id}")
    suspend fun getUserDetail(@Path("id") id: String): UserResponse
}
