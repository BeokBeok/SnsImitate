package com.beok.domain.content.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardsItem(

	@Json(name = "user_id")
	val userId: Int = -1,

	@Json(name = "img_url")
	val imgUrl: String = "",

	@Json(name = "description")
	val description: String = "",

	@Json(name = "id")
	val id: Int = -1
)
