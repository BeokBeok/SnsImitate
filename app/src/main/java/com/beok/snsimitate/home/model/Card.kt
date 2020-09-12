package com.beok.snsimitate.home.model

import com.beok.domain.content.entity.PopularCardsItem

data class Card(
    val id: Int = -1,
    val imgUrl: String = "",
    val description: String = "",
)

fun PopularCardsItem.mapToVo() = Card(id = id, imgUrl = imgUrl, description = description)
