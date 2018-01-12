package com.numero.mvp_example.api.call

import com.numero.mvp_example.model.User

import io.reactivex.Observable
import retrofit2.http.GET

interface UsersCall {

    @get:GET("/users")
    val userList: Observable<List<User>>
}
