package com.ariefzuhri.suitmediainterntest.di

import com.ariefzuhri.suitmediainterntest.data.repository.UserRepository
import com.ariefzuhri.suitmediainterntest.data.source.remote.UserPagingSource
import com.ariefzuhri.suitmediainterntest.data.source.remote.network.ApiConfig

object Injection {

    fun provideUserRepository(): UserRepository {
        val apiService = ApiConfig.provideApiService()
        val userPagingSource = UserPagingSource.getInstance(apiService)

        return UserRepository.getInstance(userPagingSource)
    }
}