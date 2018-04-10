package com.numero.mvp_example.contract

import com.numero.mvp_example.model.Post
import com.numero.mvp_example.presenter.IPresenter
import com.numero.mvp_example.view.IView

interface PostListContract {

    interface View : IView<Presenter> {
        fun showPostList(postList: List<Post>)

        fun clearPostList()

        fun showEmptyMessage()

        fun showErrorMessage(e: Throwable?)

        fun showProgress()

        fun dismissProgress()
    }

    interface Presenter : IPresenter {
        fun loadPostList()
    }
}