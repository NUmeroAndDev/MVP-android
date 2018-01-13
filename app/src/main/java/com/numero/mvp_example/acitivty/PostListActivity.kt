package com.numero.mvp_example.acitivty

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.numero.mvp_example.R
import com.numero.mvp_example.fragment.PostListFragment
import com.numero.mvp_example.presenter.PostListPresenter

import kotlinx.android.synthetic.main.activity_user_list.*

class PostListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        val userId: Long = intent.getLongExtra(BUNDLE_USER_ID, 0)

        var postListFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.container)
        if (postListFragment == null) {
            postListFragment = PostListFragment.newInstance()
            replaceFragment(postListFragment)
        }
        if (postListFragment is PostListFragment) {
            PostListPresenter(applicationContext, userId, postListFragment)
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
        private val BUNDLE_USER_ID: String = "BUNDLE_USER_ID"
        fun createIntent(context: Context, userId: Long): Intent = Intent(context, PostListActivity::class.java).apply {
            putExtra(BUNDLE_USER_ID, userId)
        }
    }
}
