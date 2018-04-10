package com.numero.mvp_example.extension

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.replaceFragment(@IdRes res: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(res, fragment).commit()
}