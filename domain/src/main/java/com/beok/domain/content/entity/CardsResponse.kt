package com.beok.domain.content.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardsResponse(

	@Json(name = "cards")
	val cards: List<CardsItem> = emptyList(),

	@Json(name = "ok")
	val ok: Boolean = false
)
