package com.rchyn.testsuitmedia.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.rchyn.testsuitmedia.data.paging.UserPagingSource
import com.rchyn.testsuitmedia.data.retrofit.ReqresApi
import com.rchyn.testsuitmedia.model.User

class UserRepositoryImpl private constructor(
    private val reqresApi: ReqresApi
) : UserRepository {

    override fun getAllUser(): LiveData<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1
            ),
            pagingSourceFactory = {
                UserPagingSource(reqresApi)
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var INSTANCE: UserRepositoryImpl? = null

        fun getInstance(
            reqresApi: ReqresApi
        ): UserRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserRepositoryImpl(reqresApi)
            }.also { INSTANCE = it }
    }
}