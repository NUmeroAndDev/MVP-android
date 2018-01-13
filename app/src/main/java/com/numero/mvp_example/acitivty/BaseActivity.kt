package com.numero.mvp_example.acitivty

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.numero.mvp_example.R

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}