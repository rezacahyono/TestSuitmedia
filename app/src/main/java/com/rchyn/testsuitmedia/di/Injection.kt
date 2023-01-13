package com.rchyn.testsuitmedia.di

import com.rchyn.testsuitmedia.data.repository.UserRepository
import com.rchyn.testsuitmedia.data.repository.UserRepositoryImpl
import com.rchyn.testsuitmedia.data.retrofit.ApiConfig

object Injection {

    fun provideUserRepository(): UserRepository {
        val reqresApi = ApiConfig.getInstance()
        return UserRepositoryImpl.getInstance(reqresApi)
    }
}