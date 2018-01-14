package com.numero.mvp_example.acitivty

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.numero.mvp_example.R
import com.numero.mvp_example.fragment.UserListFragment
import com.numero.mvp_example.presenter.UserListPresenter

import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        setSupportActionBar(toolbar)

        val userListFragment: UserListFragment = supportFragmentManager.findFragmentById(R.id.container) as UserListFragment? ?:
                UserListFragment.newInstance().also {
                    replaceFragment(it)
                }
        UserListPresenter(applicationContext, userListFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
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
