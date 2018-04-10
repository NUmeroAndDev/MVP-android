package com.numero.mvp_example.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.numero.mvp_example.R
import com.numero.mvp_example.model.Post
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_post.*

class PostListAdapter : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    var postList: List<Post> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun clear() {
        postList = listOf()
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

    class PostViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun setPost(post: Post) {
            titleTextView.text = post.title
            bodyTextView.text = post.body
        }
    }
}