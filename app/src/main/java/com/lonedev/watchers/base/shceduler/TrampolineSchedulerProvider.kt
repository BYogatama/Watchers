/*
 * Created by Bagus Yogatama on 8/28/19 9:13 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 8/28/19 9:13 PM
 */

package com.lonedev.watchers.base.shceduler

import com.lonedev.moviecatalogue.base.shceduler.SchedulerProvider
import io.reactivex.schedulers.Schedulers

class TrampolineSchedulerProvider : SchedulerProvider {
    override fun computation() = Schedulers.trampoline()
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}