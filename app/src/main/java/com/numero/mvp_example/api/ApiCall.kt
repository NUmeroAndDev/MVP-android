package com.numero.mvp_example.api

import com.numero.mvp_example.model.Post
import com.numero.mvp_example.model.User
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {

    @GET("/posts")
    fun postList(@Query("userId") userId: Long): Deferred<List<Post>>

    @GET("/users")
    fun userList(): Deferred<List<User>>
}