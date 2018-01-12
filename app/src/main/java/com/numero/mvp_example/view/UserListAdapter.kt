package com.numero.mvp_example.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.mvp_example.R
import com.numero.mvp_example.model.User
import kotlinx.android.synthetic.main.view_holder_user.view.*

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val userList: MutableList<User> = mutableListOf()

    fun setUserList(deviceInfoList: List<User>) {
        this.userList.clear()
        this.userList.addAll(deviceInfoList)
        notifyDataSetChanged()
    }

    fun clear() {
        this.userList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.setUser(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setUser(user: User) {
            itemView.nameTextView.text = user.name
        }
    }
}