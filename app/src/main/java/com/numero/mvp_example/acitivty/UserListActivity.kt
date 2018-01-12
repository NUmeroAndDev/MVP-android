package com.numero.mvp_example.acitivty

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.numero.mvp_example.R
import com.numero.mvp_example.fragment.UserListFragment
import com.numero.mvp_example.presenter.UserListPresenter

import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        setSupportActionBar(toolbar)

        var deviceListFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.container)
        if (deviceListFragment == null) {
            deviceListFragment = UserListFragment.newInstance()
            replaceFragment(deviceListFragment)
        }
        if (deviceListFragment is UserListFragment) {
            UserListPresenter(applicationContext, deviceListFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}
