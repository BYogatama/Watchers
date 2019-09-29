package com.lonedev.watchers.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity<Binding : ViewDataBinding> :
    DaggerAppCompatActivity() {

    @LayoutRes
    abstract fun setLayoutRes() : Int

    @Inject
    lateinit var factory: ViewModelFactory

    lateinit var binding: Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, setLayoutRes())
    }
}