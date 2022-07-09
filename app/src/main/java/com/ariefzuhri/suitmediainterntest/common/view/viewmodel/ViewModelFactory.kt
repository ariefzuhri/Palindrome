package com.ariefzuhri.suitmediainterntest.common.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ariefzuhri.suitmediainterntest.data.repository.UserRepository
import com.ariefzuhri.suitmediainterntest.di.Injection
import com.ariefzuhri.suitmediainterntest.ui.third.ThirdViewModel

class ViewModelFactory private constructor(
    private val userRepository: UserRepository,
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideUserRepository(),
                )
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(ThirdViewModel::class.java) -> {
                ThirdViewModel(userRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
}