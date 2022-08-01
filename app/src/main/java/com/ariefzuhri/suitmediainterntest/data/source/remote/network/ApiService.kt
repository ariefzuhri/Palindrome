package com.ariefzuhri.suitmediainterntest.data.source.remote.network

import com.ariefzuhri.suitmediainterntest.data.source.remote.response.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") size: Int,
    ): UsersResponse
}