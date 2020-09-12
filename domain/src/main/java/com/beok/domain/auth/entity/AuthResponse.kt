package com.beok.domain.auth.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse(
    @Json(name = "user_id")
    val userId: Int = -1,

    @Json(name = "ok")
    val ok: Boolean = false,

    @Json(name = "msg")
    val msg: String? = null
)
