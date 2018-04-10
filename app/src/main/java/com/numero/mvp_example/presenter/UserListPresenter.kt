package com.numero.mvp_example.presenter

import com.numero.mvp_example.contract.UserListContract
import com.numero.mvp_example.model.User
import com.numero.mvp_example.repository.IApiRepository
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class UserListPresenter(private val apiRepository: IApiRepository, private val view: UserListContract.View) : UserListContract.Presenter {

    private val job = Job()

    init {
        view.setPresenter(this)
    }

    override fun subscribe() {
        view.clearUserList()
        launch(job + UI) {
            executeLoadUserList()
        }
    }

    override fun unSubscribe() {
        job.cancel()
    }

    override fun selectUser(user: User) {
        view.selectUser(user)
    }

    override fun loadUserList() {
        launch(job + UI) {
            executeLoadUserList()
        }
    }

    private suspend fun executeLoadUserList() {
        view.showProgress()
        try {
            val userList = apiRepository.loadUserList()
            view.dismissProgress()
            if (userList.isEmpty()) {
                view.showEmptyMessage()
            } else {
                view.showUserList(userList)
            }
        } catch (t: Throwable) {
            view.dismissProgress()
            view.showErrorMessage(t)
        }
    }
}