package com.rchyn.testsuitmedia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rchyn.testsuitmedia.data.repository.UserRepository
import com.rchyn.testsuitmedia.di.Injection
import com.rchyn.testsuitmedia.ui.third_screen.ThirdScreenViewModel

class ViewModelFactory(
    private val userRepository: UserRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)){
            return ThirdScreenViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injection.provideUserRepository())
            }.also { INSTANCE = it }
    }
}