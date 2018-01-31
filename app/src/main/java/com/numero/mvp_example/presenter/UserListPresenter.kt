package com.numero.mvp_example.presenter

import com.numero.mvp_example.api.ApiCall
import com.numero.mvp_example.contract.UserListContract
import com.numero.mvp_example.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserListPresenter(private val apiCall: ApiCall, private val view: UserListContract.View) : UserListContract.Presenter {

    private var disposable: Disposable? = null

    init {
        view.setPresenter(this)
    }

    override fun subscribe() {
        view.clearUserList()
        executeLoadUserList()
    }

    override fun unSubscribe() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    override fun selectUser(user: User) {
        view.selectUser(user)
    }

    override fun loadUserList() {
        executeLoadUserList()
    }

    private fun executeLoadUserList() {
        view.showProgress()
        disposable = apiCall.userList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.dismissProgress()
                    if (it.isEmpty()) {
                        view.showEmptyMessage()
                    } else {
                        view.showUserList(it)
                    }
                }, {
                    view.dismissProgress()
                    view.showErrorMessage(it)
                })
    }

}