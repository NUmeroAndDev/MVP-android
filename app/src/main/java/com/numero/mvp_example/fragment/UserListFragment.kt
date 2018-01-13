package com.numero.mvp_example.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.mvp_example.R
import com.numero.mvp_example.contract.UserListContract
import com.numero.mvp_example.model.User
import com.numero.mvp_example.view.UserListAdapter
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment : Fragment(), UserListContract.View, UserListAdapter.OnClickListener {

    private lateinit var presenter: UserListContract.Presenter
    private val userListAdapter: UserListAdapter = UserListAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            adapter = userListAdapter
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

    override fun setPresenter(presenter: UserListContract.Presenter) {
        this.presenter = presenter
    }

    override fun clearUserList() {
        userListAdapter.clear()
    }

    override fun selectUser(user: User) {

    }

    override fun showUserList(userList: List<User>) {
        userListAdapter.setUserList(userList)
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

    override fun onClickUser(user: User) {
        presenter.selectUser(user)
    }

    companion object {
        fun newInstance(): UserListFragment = UserListFragment()
    }
}
