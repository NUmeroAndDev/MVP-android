package com.numero.mvp_example.presenter

import android.content.Context
import com.numero.mvp_example.api.ApiClient
import com.numero.mvp_example.contract.PostListContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PostListPresenter(context: Context, private val userId: Long, private val view: PostListContract.View) : PostListContract.Presenter {

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