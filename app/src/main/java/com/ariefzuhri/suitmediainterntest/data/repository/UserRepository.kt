package com.ariefzuhri.suitmediainterntest.data.repository

import androidx.paging.*
import com.ariefzuhri.suitmediainterntest.data.model.User
import com.ariefzuhri.suitmediainterntest.data.model.toUser
import com.ariefzuhri.suitmediainterntest.data.source.remote.UserPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository private constructor(
    private val userPagingSource: UserPagingSource,
) {

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            userPagingSource: UserPagingSource,
        ): UserRepository {
            return instance ?: synchronized(this) {
                instance ?: UserRepository(userPagingSource)
            }
        }
    }

    fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                userPagingSource
            }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toUser()
            }
        }
    }
}