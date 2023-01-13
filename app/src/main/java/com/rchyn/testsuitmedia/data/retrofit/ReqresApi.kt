package com.rchyn.testsuitmedia.data.retrofit

import com.rchyn.testsuitmedia.data.dto.UserDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresApi {
    @GET("users")
    suspend fun getAllUser(
        @Query("page") page: Int
    ): UserDataDto
}