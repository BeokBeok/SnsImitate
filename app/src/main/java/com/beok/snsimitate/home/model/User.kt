package com.beok.snsimitate.home.model

import com.beok.domain.content.entity.UserResponse
import com.beok.domain.content.entity.UsersItem

data class User(val id: Int = -1, val nickname: String = "", val introduction: String = "")

fun UsersItem.mapToVo() = User(id = id, nickname = nickname, introduction = introduction)

fun UserResponse.mapToVo() = user.mapToVo()
