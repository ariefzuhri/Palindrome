package com.ariefzuhri.suitmediainterntest.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ariefzuhri.suitmediainterntest.data.source.remote.network.ApiService
import com.ariefzuhri.suitmediainterntest.data.source.remote.response.UsersResponse
import retrofit2.HttpException
import java.io.IOException

class UserRemoteDataSource private constructor(
    private val apiService: ApiService,
) : PagingSource<Int, UsersResponse.DataItem>() {

    companion object {
        private const val STARTING_PAGE_NUMBER = 1

        @Volatile
        private var instance: UserRemoteDataSource? = null

        fun getInstance(apiService: ApiService): UserRemoteDataSource {
            return instance ?: synchronized(this) {
                instance ?: UserRemoteDataSource(apiService)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UsersResponse.DataItem> {
        val nextPageNumber = params.key ?: STARTING_PAGE_NUMBER
        return try {
            val response = apiService.getUsers(page = nextPageNumber, size = params.loadSize)
            LoadResult.Page(
                data = response.data as List<UsersResponse.DataItem>,
                prevKey = null,
                nextKey = if (nextPageNumber < (response.totalPages ?: 0)) nextPageNumber + 1
                else null
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UsersResponse.DataItem>): Int? {
        return null
    }
}