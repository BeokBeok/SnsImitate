package com.beok.snsimitate.home.model

import com.beok.domain.content.entity.HomeResponse
import com.beok.domain.content.entity.PopularCardsItem
import com.beok.domain.content.entity.PopularUsersItem

data class Home(
    val popularUsers: List<User> = emptyList(),
    val popularCards: List<Card> = emptyList(),
) {

    fun isNotValidUsers() = popularUsers.isEmpty()

    fun isNotValidCards() = popularCards.isEmpty()
}

fun HomeResponse.mapToVo() = Home(
    popularUsers = popularUsers.map(PopularUsersItem::mapToVo),
    popularCards = popularCards.map(PopularCardsItem::mapToVo)
)
