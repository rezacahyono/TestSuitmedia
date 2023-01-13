package com.rchyn.testsuitmedia.data.mapper

import com.rchyn.testsuitmedia.data.dto.UserDto
import com.rchyn.testsuitmedia.model.User
import com.rchyn.testsuitmedia.utils.mergeName

fun UserDto.toUser(): User {
    return User(
        avatar,
        mergeName(firstName, lastName),
        email
    )
}