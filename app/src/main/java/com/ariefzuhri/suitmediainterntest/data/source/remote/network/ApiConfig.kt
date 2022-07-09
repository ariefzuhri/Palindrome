package com.ariefzuhri.suitmediainterntest.data.source.remote.network

import com.ariefzuhri.suitmediainterntest.BuildConfig.*
import com.ariefzuhri.suitmediainterntest.common.util.getHostName
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    private const val NETWORK_TIMEOUT_SECONDS = 120L

    private fun provideOkHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private fun provideCertificatePinner(): CertificatePinner {
        val hostName = REQRES_BASE_URL.getHostName()
        return CertificatePinner.Builder()
            .add(hostName, REQRES_PUBLIC_KEY_1)
            .add(hostName, REQRES_PUBLIC_KEY_2)
            .add(hostName, REQRES_PUBLIC_KEY_3)
            .add(hostName, REQRES_PUBLIC_KEY_4)
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideOkHttpInterceptor())
            .connectTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .callTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .certificatePinner(provideCertificatePinner())
            .build()
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(REQRES_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    fun provideApiService(): ApiService {
        return provideRetrofit()
            .create(ApiService::class.java)
    }
}