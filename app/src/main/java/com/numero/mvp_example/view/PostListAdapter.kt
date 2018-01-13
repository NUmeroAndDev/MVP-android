package com.numero.mvp_example.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.mvp_example.R
import com.numero.mvp_example.model.Post
import kotlinx.android.synthetic.main.view_holder_post.view.*

class PostListAdapter() : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    private val postList: MutableList<Post> = mutableListOf()

    fun setUserList(postList: List<Post>) {
        this.postList.clear()
        this.postList.addAll(postList)
        notifyDataSetChanged()
    }

    fun clear() {
        this.postList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.apply {
            setPost(postList[position])
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setPost(post: Post) {
            itemView.titleTextView.text = post.title
            itemView.bodyTextView.text = post.body
        }
    }
}