package com.rchyn.testsuitmedia.data.dto

import com.google.gson.annotations.SerializedName

data class UserDto(

	@SerializedName("last_name")
	val lastName: String,

	@SerializedName("id")
	val id: Int,

	@SerializedName("avatar")
	val avatar: String,

	@SerializedName("first_name")
	val firstName: String,

	@SerializedName("email")
	val email: String
)