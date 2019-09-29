package com.lonedev.watchers.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.lonedev.watchers.R
import com.lonedev.watchers.base.BaseActivity
import com.lonedev.watchers.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var viewModel: MainViewModel

    override fun setLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        this.binding.handler = this
        this.binding.manager = supportFragmentManager
    }
}