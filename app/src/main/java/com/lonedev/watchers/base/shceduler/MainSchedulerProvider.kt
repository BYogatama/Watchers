/*
 * Created by Bagus Yogatama on 8/28/19 9:12 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 8/28/19 9:12 PM
 */

package com.lonedev.watchers.base.shceduler

import com.lonedev.moviecatalogue.base.shceduler.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainSchedulerProvider : SchedulerProvider {
    override fun computation() = Schedulers.computation()
    override fun ui() = AndroidSchedulers.mainThread()
    override fun io() = Schedulers.io()
}