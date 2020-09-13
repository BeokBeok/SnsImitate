package com.beok.domain.content.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(

    @Json(name = "ok")
    val ok: Boolean = false,

    @Json(name = "user")
    val user: UsersItem = UsersItem()
)
