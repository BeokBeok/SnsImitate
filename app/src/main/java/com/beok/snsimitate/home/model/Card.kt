package com.beok.snsimitate.home.model

import com.beok.domain.content.entity.CardsItem

data class Card(
    val id: Int = -1,
    val imgUrl: String = "",
    val description: String = "",
)

fun CardsItem.mapToVo() = Card(id = id, imgUrl = imgUrl, description = description)
