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
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment : Fragment(), UserListContract.View {

    private lateinit var presenter: UserListContract.Presenter

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
//            adapter =
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

    override fun selectUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showUserList(userList: List<User>) {
        
    }

    override fun showEmptyMessage() {
        messageTextView.apply {
            visibility = View.VISIBLE
            text = "No user"
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
        fun newInstance(): UserListFragment = UserListFragment()
    }
}
