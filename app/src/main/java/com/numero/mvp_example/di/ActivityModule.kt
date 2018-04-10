package com.numero.mvp_example.di

import com.numero.mvp_example.activity.PostListActivity
import com.numero.mvp_example.activity.UserListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributePostListActivity(): PostListActivity

    @ContributesAndroidInjector
    abstract fun contributeUserListActivity(): UserListActivity
}