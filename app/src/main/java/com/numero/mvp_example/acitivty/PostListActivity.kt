package com.numero.mvp_example.acitivty

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.numero.mvp_example.R
import com.numero.mvp_example.fragment.PostListFragment
import com.numero.mvp_example.model.User
import com.numero.mvp_example.presenter.PostListPresenter

import kotlinx.android.synthetic.main.activity_user_list.*

class PostListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        setSupportActionBar(toolbar)

        val user = intent.getSerializableExtra(BUNDLE_USER)
        if (user !is User) {
            finish()
            return
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "${user.name} 's Posts"
        }

        var postListFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.container)
        if (postListFragment == null) {
            postListFragment = PostListFragment.newInstance()
            replaceFragment(postListFragment)
        }
        if (postListFragment is PostListFragment) {
            PostListPresenter(applicationContext, user, postListFragment)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val BUNDLE_USER: String = "BUNDLE_USER"
        fun createIntent(context: Context, user: User): Intent = Intent(context, PostListActivity::class.java).apply {
            putExtra(BUNDLE_USER, user)
        }
    }
}
