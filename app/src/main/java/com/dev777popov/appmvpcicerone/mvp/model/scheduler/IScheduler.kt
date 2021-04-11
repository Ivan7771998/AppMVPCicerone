package com.dev777popov.appmvpcicerone.mvp.model.scheduler

import io.reactivex.rxjava3.core.Scheduler

interface IScheduler {

    fun schedulerIo(): Scheduler

    fun schedulerAndroidMain(): Scheduler
}