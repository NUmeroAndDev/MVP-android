package com.numero.mvp_example.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.mvp_example.R
import com.numero.mvp_example.contract.PostListContract
import com.numero.mvp_example.model.Post
import com.numero.mvp_example.view.PostListAdapter
import kotlinx.android.synthetic.main.fragment_post_list.*

class PostListFragment : Fragment(), PostListContract.View {

    private lateinit var presenter: PostListContract.Presenter
    private val postAdapter: PostListAdapter = PostListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            adapter = postAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unSubscribe()
    }

    override fun setPresenter(presenter: PostListContract.Presenter) {
        this.presenter = presenter
    }

    override fun showPostList(postList: List<Post>) {
        postAdapter.setUserList(postList)
    }

    override fun clearPostList() {
        postAdapter.clear()
    }

    override fun showEmptyMessage() {
        messageTextView.apply {
            visibility = View.VISIBLE
            setText(R.string.empty_user_message)
        }
    }

    override fun showErrorMessage(e: Throwable?) {
        messageTextView.apply {
            visibility = View.VISIBLE
            text = e?.message
        }
        e?.printStackTrace()
    }

    override fun showProgress() {
        progressbar.visibility = View.VISIBLE
    }

    override fun dismissProgress() {
        progressbar.visibility = View.INVISIBLE
    }

    companion object {
        fun newInstance(): PostListFragment = PostListFragment()
    }
}
