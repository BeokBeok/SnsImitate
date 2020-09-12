package com.beok.domain.content.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeResponse(

	@Json(name = "popular_users")
	val popularUsers: List<UsersItem> = emptyList(),

	@Json(name = "popular_cards")
	val popularCards: List<CardsItem> = emptyList(),

	@Json(name = "ok")
	val ok: Boolean = false
)

