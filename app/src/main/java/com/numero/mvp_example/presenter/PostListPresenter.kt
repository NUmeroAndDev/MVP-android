package com.numero.mvp_example.presenter

import com.numero.mvp_example.contract.PostListContract
import com.numero.mvp_example.model.User
import com.numero.mvp_example.repository.IApiRepository
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class PostListPresenter(private val apiRepository: IApiRepository, private var user: User, private val view: PostListContract.View) : PostListContract.Presenter {

    private val job = Job()

    init {
        view.setPresenter(this)
    }

    override fun subscribe() {
        view.clearPostList()
        launch(job + UI) {
            executeLoadPostList()
        }
    }

    override fun unSubscribe() {
        job.cancel()
    }

    override fun loadPostList() {
        launch(job + UI) {
            executeLoadPostList()
        }
    }

    private suspend fun executeLoadPostList() {
        if (user.id == null) {
            view.showErrorMessage(Exception("User id is null"))
        }
        val userId: Long = user.id ?: return
        view.showProgress()
        try {
            val postList = apiRepository.loadPostList(userId)
            view.dismissProgress()
            if (postList.isEmpty()) {
                view.showEmptyMessage()
            } else {
                view.showPostList(postList)
            }
        } catch (t: Throwable) {
            view.dismissProgress()
            view.showErrorMessage(t)
        }
    }

}