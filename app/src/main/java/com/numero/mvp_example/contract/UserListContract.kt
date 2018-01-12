package com.numero.mvp_example.contract

import com.numero.mvp_example.model.User
import com.numero.mvp_example.presenter.BasePresenter

interface UserListContract {

    interface View : BasePresenter.BaseView<Presenter> {
        fun selectUser(user: User)

        fun showUserList(userList: List<User>)

        fun showEmptyMessage()

        fun showErrorMessage(e: Throwable?)

        fun showProgress()

        fun dismissProgress()
    }

    interface Presenter : BasePresenter {
        fun selectUser(user: User)

        fun loadUserList()
    }
}