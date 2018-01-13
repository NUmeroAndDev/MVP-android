package com.numero.mvp_example.api.call

import com.numero.mvp_example.model.Post

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsCall {
    @GET("/posts")
    fun getPostList(@Query("userId") userId: Long): Observable<List<Post>>
}
