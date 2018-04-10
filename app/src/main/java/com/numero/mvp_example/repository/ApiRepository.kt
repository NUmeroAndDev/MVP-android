package com.numero.mvp_example.repository

import com.numero.mvp_example.api.ApiCall
import com.numero.mvp_example.model.Post
import com.numero.mvp_example.model.User
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.withContext

class ApiRepository(private val apiCall: ApiCall) : IApiRepository {

    override suspend fun loadPostList(userId: Long): List<Post> = withContext(CommonPool) {
        apiCall.postList(userId).await()
    }

    override suspend fun loadUserList(): List<User> = withContext(CommonPool) {
        apiCall.userList().await()
    }
}