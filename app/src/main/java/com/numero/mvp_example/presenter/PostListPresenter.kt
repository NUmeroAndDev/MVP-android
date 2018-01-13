package com.numero.mvp_example.presenter

import android.content.Context
import com.numero.mvp_example.api.ApiClient
import com.numero.mvp_example.contract.PostListContract
import com.numero.mvp_example.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PostListPresenter(context: Context, private var user: User, private val view: PostListContract.View) : PostListContract.Presenter {

    private val apiClient: ApiClient = ApiClient(context)
    private var disposable: Disposable? = null

    init {
        view.setPresenter(this)
    }

    override fun subscribe() {
        view.clearPostList()
        executeLoadPostList()
    }

    override fun unSubscribe() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    override fun loadPostList() {
        executeLoadPostList()
    }

    private fun executeLoadPostList() {
        if (user.id == null) {
            view.showErrorMessage(Exception("User id is null"))
        }
        val userId: Long = user.id ?: return
        view.showProgress()
        disposable = apiClient.loadPostList(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.dismissProgress()
                    if (it.isEmpty()) {
                        view.showEmptyMessage()
                    } else {
                        view.showPostList(it)
                    }
                }, {
                    view.dismissProgress()
                    view.showErrorMessage(it)
                })
    }

}