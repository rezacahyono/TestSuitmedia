package com.rchyn.testsuitmedia.data.dto

import com.google.gson.annotations.SerializedName

data class UserDataDto(

	@SerializedName("per_page")
	val perPage: Int,

	@SerializedName("total")
	val total: Int,

	@SerializedName("data")
	val data: List<UserDto>,

	@SerializedName("page")
	val page: Int,

	@SerializedName("total_pages")
	val totalPages: Int,

	@SerializedName("support")
	val support: Support
)