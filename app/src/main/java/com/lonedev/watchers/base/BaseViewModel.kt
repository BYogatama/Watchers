package com.lonedev.watchers.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun disposeSubscriber() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }

}