package com.numero.mvp_example.api

import com.numero.mvp_example.model.Post
import com.numero.mvp_example.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {

    @GET("/posts")
    fun postList(@Query("userId") userId: Long): Observable<List<Post>>

    @GET("/users")
    fun userList(): Observable<List<User>>
}