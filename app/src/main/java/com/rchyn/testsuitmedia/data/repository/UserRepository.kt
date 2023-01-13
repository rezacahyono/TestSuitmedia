package com.rchyn.testsuitmedia.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.rchyn.testsuitmedia.model.User


interface UserRepository {

    fun getAllUser(): LiveData<PagingData<User>>

}