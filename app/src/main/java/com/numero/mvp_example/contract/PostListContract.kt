package com.numero.mvp_example.contract

import com.numero.mvp_example.model.Post
import com.numero.mvp_example.presenter.BasePresenter

interface PostListContract {

    interface View : BasePresenter.BaseView<Presenter> {
        fun showPostList(postList: List<Post>)

        fun clearPostList()

        fun showEmptyMessage()

        fun showErrorMessage(e: Throwable?)

        fun showProgress()

        fun dismissProgress()
    }

    interface Presenter : BasePresenter {
        fun loadPostList()
    }
}