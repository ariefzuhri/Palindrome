package com.ariefzuhri.suitmediainterntest.ui.third

import androidx.lifecycle.*
import androidx.paging.*
import com.ariefzuhri.suitmediainterntest.data.model.User
import com.ariefzuhri.suitmediainterntest.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class ThirdViewModel(userRepository: UserRepository) : ViewModel() {

    val users: Flow<PagingData<User>> =
        userRepository.getUsers().cachedIn(viewModelScope)
}