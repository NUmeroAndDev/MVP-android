package com.numero.mvp_example.contract

import com.numero.mvp_example.presenter.BasePresenter

interface MainContract {

    interface View : BasePresenter.BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {

    }
}