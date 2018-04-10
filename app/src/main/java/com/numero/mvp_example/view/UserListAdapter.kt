package com.numero.mvp_example.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.mvp_example.R
import com.numero.mvp_example.model.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_user.*

class UserListAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    var userList: List<User> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun clear() {
        userList = listOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.apply {
            setUser(userList[position])
            itemView.setOnClickListener {
                onClickListener.onClickUser(userList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    interface OnClickListener {
        fun onClickUser(user: User)
    }

    class UserViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun setUser(user: User) {
            nameTextView.text = user.name
            userNameTextView.text = user.userName
        }
    }
}