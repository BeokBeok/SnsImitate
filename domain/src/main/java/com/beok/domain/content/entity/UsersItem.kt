package com.beok.domain.content.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersItem(

	@Json(name = "nickname")
	val nickname: String = "",

	@Json(name = "id")
	val id: Int = -1,

	@Json(name = "introduction")
	val introduction: String = ""
)
