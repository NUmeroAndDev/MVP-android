package com.numero.mvp_example.api

import android.content.Context

import com.numero.mvp_example.R
import com.numero.mvp_example.api.call.UsersCall
import com.numero.mvp_example.model.User

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient(context: Context) {

    private val retrofit: Retrofit

    init {
        val client = OkHttpUtil().client

        retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    fun loadUserList(): Observable<List<User>> {
        return retrofit.create(UsersCall::class.java).userList
    }
}
