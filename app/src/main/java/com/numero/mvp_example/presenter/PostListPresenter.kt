package com.numero.mvp_example.presenter

import com.numero.mvp_example.contract.PostListContract
import com.numero.mvp_example.model.User
import com.numero.mvp_example.repository.IApiRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class PostListPresenter(private val apiRepository: IApiRepository, private var user: User, private val view: PostListContract.View) : PostListContract.Presenter {

    private var disposable: Disposable? = null

    init {
        view.setPresenter(this)
    }

    override fun subscribe() {
        view.clearPostList()
        executeLoadPostList()
    }

    override fun unSubscribe() {
        disposable?.apply {
            if (isDisposed.not()) {
                dispose()
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
        disposable = apiRepository.loadPostList(userId)
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