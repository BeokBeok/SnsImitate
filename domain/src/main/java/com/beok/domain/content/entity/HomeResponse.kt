package com.beok.domain.content.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeResponse(

	@Json(name = "popular_users")
	val popularUsers: List<PopularUsersItem> = emptyList(),

	@Json(name = "popular_cards")
	val popularCards: List<PopularCardsItem> = emptyList(),

	@Json(name = "ok")
	val ok: Boolean = false
)

