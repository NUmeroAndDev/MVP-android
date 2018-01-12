package com.numero.mvp_example.api

import com.numero.mvp_example.BuildConfig
import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class OkHttpUtil {

    private val builder: OkHttpClient.Builder = OkHttpClient.Builder()

    val client: OkHttpClient
        get() = builder.build()

    init {
        builder.apply {
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }

        }

    }
}
