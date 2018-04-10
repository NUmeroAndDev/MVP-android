package com.numero.mvp_example.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.numero.mvp_example.R
import com.numero.mvp_example.api.ApiCall
import com.numero.mvp_example.extension.replaceFragment
import com.numero.mvp_example.fragment.UserListFragment
import com.numero.mvp_example.presenter.UserListPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_user_list.*
import javax.inject.Inject

class UserListActivity : AppCompatActivity() {

    @Inject
    lateinit var apiCall: ApiCall

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        setSupportActionBar(toolbar)

        val userListFragment: UserListFragment = supportFragmentManager.findFragmentById(R.id.container) as? UserListFragment
                ?: UserListFragment.newInstance().also {
                    replaceFragment(R.id.container, it)
                }
        UserListPresenter(apiCall, userListFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_user_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_licenses -> {
                startActivity(LicensesActivity.createIntent(applicationContext))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
