package com.beok.snsimitate.home.model

import com.beok.domain.content.entity.CardsItem
import com.beok.domain.content.entity.HomeResponse
import com.beok.domain.content.entity.UsersItem
import com.beok.snsimitate.card.model.Card
import com.beok.snsimitate.card.model.mapToVo

data class Home(
    val popularUsers: List<User> = emptyList(),
    val popularCards: List<Card> = emptyList(),
)

fun HomeResponse.mapToVo() = Home(
    popularUsers = popularUsers.map(UsersItem::mapToVo),
    popularCards = popularCards.map(CardsItem::mapToVo)
)
