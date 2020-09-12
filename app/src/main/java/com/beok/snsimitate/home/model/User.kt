package com.beok.snsimitate.home.model

import com.beok.domain.content.entity.PopularUsersItem

data class User(val nickname: String, val introduction: String)

fun PopularUsersItem.mapToVo() = User(nickname = nickname, introduction = introduction)
