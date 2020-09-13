package com.beok.snsimitate.home.model

import com.beok.domain.content.entity.UsersItem

data class User(val nickname: String = "", val introduction: String = "")

fun UsersItem.mapToVo() = User(nickname = nickname, introduction = introduction)
