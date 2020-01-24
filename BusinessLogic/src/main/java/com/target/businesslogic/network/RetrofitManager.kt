package com.target.businesslogic.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Pankaj Nimgade on 1/19/2020.
 */
class RetrofitManager {

    /**
     * [isDebug] when true, we want to see the HttpLogging in the logcat, otherwise omit it.
     */
    fun buildWithBaseUrl(baseUrl: String, isDebug: Boolean = false): Retrofit {

        val client = if (isDebug) {
            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        } else {
            OkHttpClient.Builder()
                .build()
        }
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}