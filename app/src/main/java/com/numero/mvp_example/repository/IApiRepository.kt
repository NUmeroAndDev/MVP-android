package com.numero.mvp_example.repository

import com.numero.mvp_example.model.Post
import com.numero.mvp_example.model.User

interface IApiRepository {

    suspend fun loadPostList(userId: Long): List<Post>

    suspend fun loadUserList(): List<User>
}