package com.ariefzuhri.suitmediainterntest.di

import com.ariefzuhri.suitmediainterntest.data.repository.UserRepository
import com.ariefzuhri.suitmediainterntest.data.source.remote.UserRemoteDataSource
import com.ariefzuhri.suitmediainterntest.data.source.remote.network.ApiConfig

object Injection {

    fun provideUserRepository(): UserRepository {
        val apiService = ApiConfig.provideApiService()
        val userRemoteDataSource = UserRemoteDataSource.getInstance(apiService)

        return UserRepository.getInstance(userRemoteDataSource)
    }
}