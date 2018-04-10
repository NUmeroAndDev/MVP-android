package com.numero.mvp_example.repository

import com.numero.mvp_example.model.Post
import com.numero.mvp_example.model.User
import io.reactivex.Observable

interface IApiRepository {

    fun loadPostList(userId: Long): Observable<List<Post>>

    fun loadUserList(): Observable<List<User>>
}