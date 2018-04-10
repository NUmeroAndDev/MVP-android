package com.numero.mvp_example.presenter

import com.numero.mvp_example.contract.UserListContract
import com.numero.mvp_example.model.User
import com.numero.mvp_example.repository.IApiRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class UserListPresenter(private val apiRepository: IApiRepository, private val view: UserListContract.View) : UserListContract.Presenter {

    private var disposable: Disposable? = null

    init {
        view.setPresenter(this)
    }

    override fun subscribe() {
        view.clearUserList()
        executeLoadUserList()
    }

    override fun unSubscribe() {
        disposable?.apply {
            if (isDisposed.not()) {
                dispose()
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
        disposable = apiRepository.loadUserList()
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