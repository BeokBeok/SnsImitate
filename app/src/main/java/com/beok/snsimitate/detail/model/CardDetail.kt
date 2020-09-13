package com.beok.snsimitate.detail.model

import com.beok.domain.content.entity.CardDetailResponse
import com.beok.domain.content.entity.CardsItem
import com.beok.snsimitate.card.model.Card
import com.beok.snsimitate.card.model.mapToVo
import com.beok.snsimitate.home.model.User
import com.beok.snsimitate.home.model.mapToVo

data class CardDetail(
    val card: Card = Card(),
    val user: User = User(),
    val recommendCards: List<Card> = emptyList()
)

fun CardDetailResponse.mapToVo() = CardDetail(
    card = card.mapToVo(),
    user = user.mapToVo(),
    recommendCards = recommendCards.map(CardsItem::mapToVo)
)
