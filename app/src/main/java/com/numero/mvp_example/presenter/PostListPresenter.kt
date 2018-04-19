package com.numero.mvp_example.presenter

import com.numero.mvp_example.contract.PostListContract
import com.numero.mvp_example.model.User
import com.numero.mvp_example.repository.IApiRepository
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.cancelChildren

class PostListPresenter(private val apiRepository: IApiRepository, private var user: User, private val view: PostListContract.View) : PostListContract.Presenter {

    private var job = Job()

    init {
        view.setPresenter(this)
    }

    override fun subscribe() {
        executeLoadPostList()
    }

    override fun unSubscribe() {
        job.cancelChildren()
    }

    override fun loadPostList() {
        executeLoadPostList()
    }

    private fun executeLoadPostList() = async(job + UI) {
        if (user.id == null) {
            view.showErrorMessage(Exception("User id is null"))
        }
        val userId: Long = user.id ?: return@async
        view.showProgress()
        try {
            val postList = apiRepository.loadPostList(userId).await()
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