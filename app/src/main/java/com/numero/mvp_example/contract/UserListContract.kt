package com.numero.mvp_example.contract

import com.numero.mvp_example.model.User
import com.numero.mvp_example.presenter.IPresenter
import com.numero.mvp_example.view.IView

interface UserListContract {

    interface View : IView<Presenter> {
        fun selectUser(user: User)

        fun showUserList(userList: List<User>)

        fun clearUserList()

        fun showEmptyMessage()

        fun showErrorMessage(e: Throwable?)

        fun showProgress()

        fun dismissProgress()
    }

    interface Presenter : IPresenter {
        fun selectUser(user: User)

        fun loadUserList()
    }
}