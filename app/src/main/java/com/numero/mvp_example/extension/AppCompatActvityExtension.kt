package com.numero.mvp_example.extension

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.numero.mvp_example.MvpExampleApplication
import com.numero.mvp_example.di.ApplicationComponent

fun AppCompatActivity.getComponent(): ApplicationComponent? {
    return (application as? MvpExampleApplication)?.applicationComponent
}

fun AppCompatActivity.replaceFragment(@IdRes res: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(res, fragment).commit()
}