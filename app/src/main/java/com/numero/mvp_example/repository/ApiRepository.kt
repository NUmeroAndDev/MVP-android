package com.numero.mvp_example.repository

import com.numero.mvp_example.api.ApiCall
import com.numero.mvp_example.model.Post
import com.numero.mvp_example.model.User
import io.reactivex.Observable

class ApiRepository(private val apiCall: ApiCall) : IApiRepository {

    override fun loadPostList(userId: Long): Observable<List<Post>> = apiCall.postList(userId)

    override fun loadUserList(): Observable<List<User>> = apiCall.userList()
}