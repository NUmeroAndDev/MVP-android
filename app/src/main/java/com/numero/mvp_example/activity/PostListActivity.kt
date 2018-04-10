package com.numero.mvp_example.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.numero.mvp_example.R
import com.numero.mvp_example.api.ApiCall
import com.numero.mvp_example.extension.replaceFragment
import com.numero.mvp_example.fragment.PostListFragment
import com.numero.mvp_example.model.User
import com.numero.mvp_example.presenter.PostListPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_user_list.*
import javax.inject.Inject

class PostListActivity : AppCompatActivity() {

    @Inject
    lateinit var apiCall: ApiCall

    private val user: User by lazy {
        intent.getSerializableExtra(BUNDLE_USER) as User
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            if (user.name != null) {
                title = "${user.name} 's Posts"
            }
        }

        val postListFragment: PostListFragment = supportFragmentManager.findFragmentById(R.id.container) as? PostListFragment
                ?: PostListFragment.newInstance().also {
                    replaceFragment(R.id.container, it)
                }
        PostListPresenter(apiCall, user, postListFragment)
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
        private const val BUNDLE_USER: String = "BUNDLE_USER"
        fun createIntent(context: Context, user: User): Intent = Intent(context, PostListActivity::class.java).apply {
            putExtra(BUNDLE_USER, user)
        }
    }
}
