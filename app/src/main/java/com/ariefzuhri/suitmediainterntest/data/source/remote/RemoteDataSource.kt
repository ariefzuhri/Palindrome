package com.ariefzuhri.suitmediainterntest.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ariefzuhri.suitmediainterntest.common.dto.ApiResponse
import com.ariefzuhri.suitmediainterntest.data.source.remote.network.ApiService
import com.ariefzuhri.suitmediainterntest.data.source.remote.response.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class RemoteDataSource private constructor(
    private val apiService: ApiService,
) {

    companion object {
        private val TAG = RemoteDataSource::class.java.simpleName.toString()

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource {
            return instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService)
            }
        }
    }

    fun getUsers(): LiveData<ApiResponse<UsersResponse>> {
        val result = MutableLiveData<ApiResponse<UsersResponse>>().apply {
            value = ApiResponse.Loading
        }

        apiService.getUsers()
            .enqueue(object : Callback<UsersResponse> {
                override fun onResponse(
                    call: Call<UsersResponse>,
                    response: Response<UsersResponse>,
                ) {
                    val data = response.body()
                    result.value = if (response.isSuccessful && data != null) {
                        if (data.data?.isNotEmpty() == true) {
                            ApiResponse.Success(data)
                        } else {
                            ApiResponse.Empty
                        }
                    } else {
                        ApiResponse.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                    val errorMessage = when (t) {
                        is IOException -> "Network failure"
                        else -> t.message ?: "Unknown error"
                    }
                    result.value = ApiResponse.Error(errorMessage)
                    Log.e(TAG, errorMessage, t)
                }
            })

        return result
    }
}