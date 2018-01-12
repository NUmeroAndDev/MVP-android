package com.numero.mvp_example.presenter

interface BasePresenter {

    fun subscribe()

    fun unSubscribe()

    interface BaseView<in T> {

        fun setPresenter(presenter: T)

    }

}