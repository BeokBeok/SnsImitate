package com.beok.domain.content.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardDetailResponse(

    @Json(name = "card")
    val card: CardsItem = CardsItem(),

    @Json(name = "user")
    val user: UsersItem = UsersItem(),

    @Json(name = "recommend_cards")
    val recommendCards: List<CardsItem> = emptyList(),

    @Json(name = "ok")
    val ok: Boolean = false
)
