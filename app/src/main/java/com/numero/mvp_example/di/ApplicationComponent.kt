package com.numero.mvp_example.di

import com.numero.mvp_example.activity.PostListActivity
import com.numero.mvp_example.activity.UserListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiModule::class)])
interface ApplicationComponent {

    fun inject(userListActivity: UserListActivity)

    fun inject(postListActivity: PostListActivity)

}