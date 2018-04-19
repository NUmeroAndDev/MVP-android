package com.numero.mvp_example.repository

import com.numero.mvp_example.api.ApiCall
import com.numero.mvp_example.model.Post
import com.numero.mvp_example.model.User
import kotlinx.coroutines.experimental.Deferred

class ApiRepository(private val apiCall: ApiCall) : IApiRepository {

    override fun loadPostList(userId: Long): Deferred<List<Post>> = apiCall.postList(userId)

    override fun loadUserList(): Deferred<List<User>> = apiCall.userList()
}