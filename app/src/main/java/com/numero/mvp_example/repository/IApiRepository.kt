package com.numero.mvp_example.repository

import com.numero.mvp_example.model.Post
import com.numero.mvp_example.model.User
import kotlinx.coroutines.experimental.Deferred

interface IApiRepository {

    fun loadPostList(userId: Long): Deferred<List<Post>>

    fun loadUserList(): Deferred<List<User>>
}