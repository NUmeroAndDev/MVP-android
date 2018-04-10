package com.numero.mvp_example.view

interface IView<in T> {

    fun setPresenter(presenter: T)

}