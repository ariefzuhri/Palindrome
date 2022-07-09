package com.ariefzuhri.suitmediainterntest.data.repository

import androidx.paging.*
import com.ariefzuhri.suitmediainterntest.data.model.User
import com.ariefzuhri.suitmediainterntest.data.model.toUser
import com.ariefzuhri.suitmediainterntest.data.source.remote.UserRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository private constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
) {

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            userRemoteDataSource: UserRemoteDataSource,
        ): UserRepository {
            return instance ?: synchronized(this) {
                instance ?: UserRepository(userRemoteDataSource)
            }
        }
    }

    fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                userRemoteDataSource
            }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toUser()
            }
        }
    }
}