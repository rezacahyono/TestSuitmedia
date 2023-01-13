package com.rchyn.testsuitmedia.ui.third_screen


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rchyn.testsuitmedia.data.repository.UserRepository
import com.rchyn.testsuitmedia.model.User

class ThirdScreenViewModel(
    userRepository: UserRepository
) : ViewModel() {

    val users: LiveData<PagingData<User>> = userRepository.getAllUser().cachedIn(viewModelScope)
}